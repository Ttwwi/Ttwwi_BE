package ttwwi.oauth2;

import java.util.Map;

import ttwwi.enums.AuthProvider;

public class OAuth2UserInfoFactory 
{

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) 
    {
        switch (authProvider) 
        {
            case KAKAO: return new KakaoOAuth2User(attributes);
//            case FACEBOOK: return new FaceBookOAuth2User(attributes);

            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}