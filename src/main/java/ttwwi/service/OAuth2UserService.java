package ttwwi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttwwi.entity.Member;
import ttwwi.enums.AuthProvider;
import ttwwi.enums.Role;
import ttwwi.oauth2.OAuth2UserInfo;
import ttwwi.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class OAuth2UserService 
{
	private final MemberRepository memberRepository;
	
	
	private Member registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) 
	{
		Member userEntity = Member.builder()
              .memberEmail(oAuth2UserInfo.getEmail())
              .memberName(oAuth2UserInfo.getName())
              .oauth2Id(oAuth2UserInfo.getOAuth2Id())
              .memberImageurl(oAuth2UserInfo.getImageUrl())
              .oauth2Provider(authProvider)
              .role(Role.ROLE_USER)
              .build();

		return memberRepository.save(userEntity);
  }
}