package ttwwi.entity;

import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trip 
{
	private Long id;
	private String title;
	private String subtitle;
	private String password;
	private String profileImage;
	private String oauth2Id;	
	

	
//	   @ManyToMany
//	   @JoinTable(name = "feed", joinColumns = {@JoinColumn (name = "oauth2Id", referencedColumnName = "oauth2Id")},
//	   inverseJoinColumns = {
//			   @JoinColumn(name = "id"), @JoinColumn(name = "title")
//	   })
//	   
//	   private Set<Feed> authorities;
}

/*
insert into "user" (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into "user" (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');
*/