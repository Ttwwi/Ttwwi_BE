package ttwwi.oauth2;

import lombok.*;
import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo 
{
    protected Map<String, Object> attributes;

    public abstract String getOAuth2Id();
    public abstract String getEmail();
    public abstract String getName();
    public abstract String getImageUrl();
}