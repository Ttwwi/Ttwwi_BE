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