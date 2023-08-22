package ttwwi.controller;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.AccessTokenDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController 
{	
	
	@RequestMapping("/oauth2/code/kakao")
	public ResponseEntity<String> KakaoAccessToken(@RequestBody AccessTokenDto accessToken) 
	{
		String info = accessToken.getAccessToken();
		return ResponseEntity.status(HttpStatus.OK).body(info);
	}

}