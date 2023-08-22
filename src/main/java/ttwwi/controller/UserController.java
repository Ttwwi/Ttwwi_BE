package ttwwi.controller;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
//	    @ResponseBody
//	    @PostMapping("/oauth2/code/kakao")
//	    public void kakaoCallback(@RequestParam String token, Model model) 
//	    {
//	    	model.addAttribute("token", token);
//	        System.out.println(token);
//	    }
	    

	    @GetMapping("/oauth2/code/kakao")
	    public void kakaoCallback(@RequestBody MyData accessToken) 
	    {
	    	
	        System.out.println("token : " + accessToken.getValue());
	        //return "Value received and printed";
	    }
	    
		public class MyData 
		{
		    private String value;

		    public String getValue() 
		    {
		        return value;
		    }

		    public void setValue(String value) 
		    {
		        this.value = value;
		    }
		}

}