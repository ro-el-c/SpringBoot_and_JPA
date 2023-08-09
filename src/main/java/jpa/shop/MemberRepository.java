package jpa.shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.shop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext // EntityManager 주입해줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
