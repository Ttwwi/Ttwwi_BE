package ttwwi.controller;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.AccessTokenDto;

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
    public String kakaojson(@RequestBody AccessTokenDto accessToken) 
    {
        System.out.println("Received JSON from frontend: " + accessToken.getKey1() + ", " + accessToken.getKey2());
        return "JSON received and printed";
    }
}