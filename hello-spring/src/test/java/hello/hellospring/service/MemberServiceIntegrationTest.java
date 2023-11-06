package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void register() {
//        given
        Member member = new Member();
        member.setName("spring");

//        when
        Long saveId = memberService.join(member);

//        then
        Member findMember = memberService.findOne(saveId).get();
//        Assertions.assertThat(member.getName().isEqualTo(findMember.getName()));
    }

}
