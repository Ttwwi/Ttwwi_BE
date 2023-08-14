package ttwwi.entity;

import lombok.*;
import ttwwi.enums.AuthProvider;
import ttwwi.enums.Role;
import ttwwi.oauth2.OAuth2UserInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email;
    
    @NotNull
    private String name;

    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String password;

    public UserEntity update(OAuth2UserInfo oAuth2UserInfo) 
    {
        this.name = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }
}
