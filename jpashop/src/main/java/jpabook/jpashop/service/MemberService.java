package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 전체에 readOnly를 추가함으로서 조회만 하는 함수의 성능 최적화
@RequiredArgsConstructor    // final 필드를 주입하는 생성자를 생성
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param member 회원 객체
     * @return 가입한 회원 아이디
     */
    @Transactional  // Transactional의 기본 값으로 readOnly를 추가하였기 때문에 값을 변경하는 함수에는 별도로 기본값인 false를 지정
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        EXCEPTION 문제 있으면 예외 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 검색
     * @return 전체 회원 리스트
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     * @param memberId
     * @return 조회된 회원
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
