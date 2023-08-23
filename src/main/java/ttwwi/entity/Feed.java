package ttwwi.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed 
{
	private Long id;
	private String writer;
	private String contents;
	
	
}
