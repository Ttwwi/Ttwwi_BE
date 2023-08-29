package ttwwi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import ttwwi.jwt.JwtFilter;
import ttwwi.jwt.JwtTokenProvider;
import ttwwi.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer
{
    private final long MAX_AGE_SECS = 3600;
	private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
	
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
        //요청에 대한 권한 설정
    	httpSecurity
    			.authorizeRequests() 															//인증되지않아도 가능
            	.antMatchers("/**", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .anyRequest().authenticated();

        //oauth2Login
    	httpSecurity
    			.oauth2Login()  	
                .redirectionEndpoint().baseUri("/login/oauth2/code/*").and()		// Authorization code와 함께 리다이렉트할 URL           
                .userInfoEndpoint().userService(customOAuth2UserService);			// 회원 정보 처리, Provider로부터 획득한 유저정보를 다룰 service class를 지정				

    	httpSecurity
    			.logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
    	
        //jwt filter 설정
        httpSecurity
        	// UsernamePasswordFilter에서 클라이언트가 요청한 리소스의 접근 권한이 없을 때 막는 역할을 하기 때문에 이 필터 전에 JwtFilter실행
        		.addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
   	
        return httpSecurity
        		.build();
    }
}