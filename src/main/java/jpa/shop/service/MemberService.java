package jpa.shop.service;

import jpa.shop.repository.MemberRepository;
import jpa.shop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드로만 구성된 생성자 자동 생성
public class MemberService {
    private final MemberRepository memberRepository; // final -> 값이 설정되었는지 컴파일 시점에 체크 가능

//    //@Autowired -> 생성자가 하나인 경우 생략 가능
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // 값이 보장됨
    }

    private void validateDuplicateMember(Member member) {
        // exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        /*
        * 동시에 호출하는 경우 둘 다 통과 가능
        * ->
        * 검사용 비즈니스 로직이 있더라도,
        * 중복을 허용하지 않는 것은 DB에 unique 제약 조건으로 잡아두는 것을 권장
        * */
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
