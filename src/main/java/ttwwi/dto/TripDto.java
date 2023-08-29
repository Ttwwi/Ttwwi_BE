package ttwwi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttwwi.entity.Member;
import ttwwi.entity.Trip;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto 
{
	
	@Size(min = 2, max = 16)
	private String title;
	
	@Size(min = 2, max = 32)
	private String subtitle;
	   
	private String tripImage;
	   
//	@NotNull
//	private Long memberId;
	   
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 4, max = 256)
	private String password;
	
	public static TripDto from(Trip trip) 
	{
		if(trip == null)
		{
			return null;
		}
		
		return TripDto.builder()
				.title(trip.getTripTitle())
				.subtitle(trip.getTripSubtitle())
				.tripImage(trip.getTripImage())
//				.memberId(member.getMemberId())
	            .build();
	}
}
