package ttwwi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.TripDto;
import ttwwi.service.MemberService;

@RestController
@RequestMapping(value = "/ttwwi")
@RequiredArgsConstructor
public class MemberController 
{
	private final MemberService memberService;
	
	@GetMapping("/")
	public void  trip()
	{
		
	}
	
	@PostMapping("/trip")
	public ResponseEntity<TripDto> tripCreate(@Valid @RequestBody TripDto tripDto)
	{
		return ResponseEntity.ok(memberService.tripCreate(tripDto));
	}

//	@PutMapping("/trip/{postid}")
}
