package ttwwi.controller;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController 
{	    /**
	     * 카카오 callback
	     * [GET] /oauth/kakao/callback
	     */
	    @ResponseBody
	    @GetMapping("/oauth2/code/kakao")
	    public void kakaoCallback(@RequestParam String code) 
	    {
	        System.out.println(code);
	    }
}