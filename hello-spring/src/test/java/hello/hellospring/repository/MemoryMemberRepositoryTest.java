package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // callBack 처럼 각 메소드 동작이 끝나면 자동으로 호출
    public void afterEach() {
//        메소드의 동작 순서에 상관없이 결과를 도출하기 위해 메소드 동작이 끝나면 repository 초기화
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);    // member와 result를 비교하여 같으면 초록불이지만 다르면 에러 반환 (jupiter)
        assertThat(member).isEqualTo(result);    // (core.api) Assertion을 로컬로 설정하여 생략
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spirng1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spirng2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spirng3");
        repository.save(member3);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }

}
