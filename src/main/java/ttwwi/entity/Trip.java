package ttwwi.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "trips")
@Getter 
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class Trip 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @Column(nullable = false)
    private String tripTitle;

    @Column
    private String tripSubtitle;

    @Column
    private String tripDescription;
    
    @Column
    private String tripImage;
    
    @Column
    private String tripSnsinvite;
    
    @Column(nullable = false)
    private String tripUuid;
    
    @Column
    private String tripPassword;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
   
}