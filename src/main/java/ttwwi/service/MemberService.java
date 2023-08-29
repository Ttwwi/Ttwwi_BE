package ttwwi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ttwwi.dto.TripDto;
import ttwwi.entity.Member;
import ttwwi.entity.Trip;
import ttwwi.repository.TripRepository;

@Service
@RequiredArgsConstructor
public class MemberService
{
	private final PasswordEncoder passwordEncoder;
	private final TripRepository tripRepository;
	
	@Transactional
	public TripDto tripCreate(TripDto tripDto)
	{
        Trip trip = Trip.builder()
        		.tripTitle(tripDto.getTitle())
				.tripSubtitle(tripDto.getSubtitle())
				.tripImage(tripDto.getTripImage())
				.tripPassword(passwordEncoder.encode(tripDto.getPassword()))
//				.member(member.getMemberId())
				
	            .build();
        
        return TripDto.from(tripRepository.save(trip));
	}
}
