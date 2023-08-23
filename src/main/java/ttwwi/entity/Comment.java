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
public class Comment 
{
	private Long id;
	private String title;
}
