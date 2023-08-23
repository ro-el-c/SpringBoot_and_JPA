package jpa.shop.service;

import jakarta.persistence.EntityManager;
import jpa.shop.domain.Member;
import jpa.shop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // JUnit 과 스프링을 같이 실행
@SpringBootTest // 없으면 @Autowired 실패
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("Chung");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // DB에 반영 -> insert 쿼리 볼 수 있음
        assertEquals(member, memberRepository.findOne(savedId));
        /*
        * 가능한 이유: @Transactional
        * */
        
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member memberA = new Member();
        memberA.setName("Chung");
        Member memberB = new Member();
        memberB.setName("Chung");

        // when
        memberService.join(memberA);
        memberService.join(memberB); // 예외 발샐해야 함

        // @Test(expected = IllegalStateException.class) 작성으로 지울 수 있는 코드
//        try {
//            memberService.join(memberB); // 예외 발샐해야 함
//        } catch (IllegalStateException e) {
//            return;
//        }

        // then
        fail("예외가 발생해야 한다."); // 이곳에 도달하면 무조건 오류 반환
    }
}