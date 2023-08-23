package ttwwi.controller;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.AccessTokenDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController 
{	
	@PostMapping("/oauth2/code/kakao")
    public ResponseEntity<String> kakaoJson(@RequestBody AccessTokenDto accessToken) 
    {
        System.out.println("Received JSON from frontend: " + accessToken.getAccessToken());
        return new ResponseEntity<> (HttpStatus.OK);
    }
}