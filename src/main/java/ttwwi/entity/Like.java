package ttwwi.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "contents_likes")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Like 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
	
    @ManyToOne
    @JoinColumn(name = "memberId")
	private Member member;
	
    @ManyToOne
    @JoinColumn(name = "contentId")
	private Content contentId;
}
