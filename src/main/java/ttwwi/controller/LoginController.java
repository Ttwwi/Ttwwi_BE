package ttwwi.controller;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.AccessTokenDto;
import ttwwi.service.CustomOAuth2UserService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login/oauth2/code")
@RequiredArgsConstructor
public class LoginController 
{	
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@PostMapping("/kakao")
	public ResponseEntity<OAuth2User> kakaoJson(@RequestBody OAuth2UserRequest accesstoken) 
	{
		System.out.println("Access Token : " + accesstoken.getAccessToken());

	    return ResponseEntity.ok(customOAuth2UserService.loadUser(accesstoken));
	}
}

//	RestTemplate restTemplate = new RestTemplate();
//	
////Access Token을 이용해서 사용자 정보를 응답 받는 코드
//HttpHeaders AccessTokenHeaders = new HttpHeaders();
//AccessTokenHeaders.add("Authorization", "Bearer " + accesstoken.getAccessToken());
//AccessTokenHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//HttpEntity<HttpHeaders> kakaoRequest = new HttpEntity<>(AccessTokenHeaders);
//ResponseEntity<String> profileResponse = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoRequest, String.class);