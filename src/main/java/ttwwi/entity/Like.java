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
public class Like 
{
	private Long id;
	private String title;
	private Long likes;
}
