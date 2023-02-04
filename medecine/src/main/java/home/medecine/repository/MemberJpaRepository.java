package home.medecine.repository;

import home.medecine.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPhone(String phone);
    Optional<Member> findByMbId(String id);
    Optional<Member> findByNameAndPhone(String name, String phone);
    Optional<Member> findByMbIdAndNameAndPhone(String id, String name, String phone);
}
