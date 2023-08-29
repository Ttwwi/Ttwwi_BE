package ttwwi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttwwi.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> 
{
    Optional<Member> findBymemberEmail(String memberEmail);
    boolean existsBymemberEmail(String memberEmail);
    
}