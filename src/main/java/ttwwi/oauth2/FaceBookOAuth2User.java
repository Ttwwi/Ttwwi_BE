package ttwwi.oauth2;

import java.util.Map;

public class FaceBookOAuth2User extends OAuth2UserInfo
{

    public FaceBookOAuth2User(Map<String, Object> attributes) 
    {
		super(attributes);
    }

        @Override
        public String getOAuth2Id() 
        {
            return (String) attributes.get("id");
        }

        @Override
        public String getEmail() 
        {
            return (String) attributes.get("email");
        }

        @Override
        public String getName() 
        {
            return (String) attributes.get("name");
        }
        
        public String getImageUrl()
        {
        	return (String) attributes.get("imageUrl");
        }
}