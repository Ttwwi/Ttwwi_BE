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
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception 
    {
        //httpBasic, csrf, formLogin, rememberMe, logout, session disable
    	httpSecurity
    	
			/*	.exceptionHandling()
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)// 인증 과정에서 생길 exception 처리
					.accessDeniedHandler(jwtAccessDeniedHandler); // 인가 과정에서 생길 Exception 처리
    		*/
    	
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	

    	

        //요청에 대한 권한 설정
    	httpSecurity
    			.authorizeRequests() 															//인증되지않아도 가능
            	.antMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/oauth2/**", "/login/**") .permitAll()
                .anyRequest().authenticated();

        //oauth2Login
    	httpSecurity
    			.oauth2Login()
    												// ex) /oauth2/authorize/kakao 
                .authorizationEndpoint().baseUri("/oauth2/authorize")							// front -> back으로 요청 보내는 소셜 로그인 URL
                .authorizationRequestRepository(cookieAuthorizationRequestRepository).and()  	// 인증 요청을 cookie 에 저장
                									// ex) //oauth2/callback/kakao
                .redirectionEndpoint().baseUri("/oauth2/code/*").and()  					// Authorization code와 함께 리다이렉트할 URL 
                
                .userInfoEndpoint().userService(customOAuth2UserService).and()  				// 회원 정보 처리, Provider로부터 획득한 유저정보를 다룰 service class를 지정
                
                .successHandler(oAuth2AuthenticationSuccessHandler)								// OAuth2 로그인 성공 시 호출되는 handler
                .failureHandler(oAuth2AuthenticationFailureHandler);							

    	httpSecurity
    			.logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
    	
        //jwt filter 설정
        httpSecurity
        		//// UsernamePasswordFilter에서 클라이언트가 요청한 리소스의 접근 권한이 없을 때 막는 역할을 하기 때문에 이 필터 전에 JwtFilter실행
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