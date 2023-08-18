package ttwwi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import ttwwi.jwt.JwtAccessDeniedHandler;
import ttwwi.jwt.JwtAuthenticationEntryPoint;
import ttwwi.jwt.JwtFilter;
import ttwwi.jwt.JwtTokenProvider;
import ttwwi.oauth2.OAuth2AuthenticationFailureHandler;
import ttwwi.oauth2.OAuth2AuthenticationSuccessHandler;
import ttwwi.repository.CookieAuthorizationRequestRepository;
import ttwwi.service.CustomOAuth2UserService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer
{
    private final long MAX_AGE_SECS = 3600;
    
	private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception 
    {
        //httpBasic, csrf, formLogin, rememberMe, logout, session disable
    	httpSecurity
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)
                ;

        //요청에 대한 권한 설정
    	httpSecurity
    			.authorizeRequests() 															//인증되지않아도 가능
            	.antMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js") .permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated();

        //oauth2Login
    	httpSecurity
    			.oauth2Login()
                .authorizationEndpoint().baseUri("/oauth2/authorize")  							// 소셜 로그인 url
                .authorizationRequestRepository(cookieAuthorizationRequestRepository).and()  	// 인증 요청을 cookie 에 저장
                .redirectionEndpoint().baseUri("/oauth2/callback/*").and()  					// 소셜 인증 후 redirect url
                
                //userService()는 OAuth2 인증 과정에서 Authentication 생성에 필요한 OAuth2User 를 반환하는 클래스를 지정한다.
                .userInfoEndpoint().userService(customOAuth2UserService).and()  				// 회원 정보 처리
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

    	httpSecurity
    			.logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
    	
        //jwt filter 설정
        httpSecurity
        		.addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
   	
        return httpSecurity
        		.build();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) 
    {
    	corsRegistry
        		.addMapping("/**")
                .allowedOrigins("/*")    													//외부에서 들어오는 모든 url 허용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")    		//허용되는 Method
                .allowedHeaders("*")    //허용되는 헤더
                .allowCredentials(true)    //자격증명 허용
                .maxAge(MAX_AGE_SECS);   //허용 시간
    }
}