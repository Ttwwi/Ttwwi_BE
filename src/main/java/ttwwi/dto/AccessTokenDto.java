package ttwwi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto 
{
	private String accessToken;
    private String key1;
    private String key2;
    
	    // Getter and setter methods
	    public String getKey1() 
	    {
	        return key1;
	    }

	    public void setKey1(String key1) 
	    {
	        this.key1 = key1;
	    }

	    public String getKey2() 
	    {
	        return key2;
	    }

	    public void setKey2(String key2) 
	    {
	        this.key2 = key2;
	    }
}