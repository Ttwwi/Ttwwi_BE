package ttwwi.entity;

import lombok.*;
import ttwwi.enums.AuthProvider;
import ttwwi.enums.Role;
import ttwwi.oauth2.OAuth2UserInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    private String memberEmail;
    
    @NotNull
    private String memberName;

    private String memberImageurl;
    
    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    private AuthProvider oauth2Provider;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    public Member update(OAuth2UserInfo oAuth2UserInfo) 
    {
        this.memberName = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();
        this.memberImageurl = oAuth2UserInfo.getImageUrl();

        return this;
    }
}
