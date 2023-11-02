package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Controller 주석을 이용한 함수는 모두 Spring 컨테이너에 자동으로 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 의존 관계 설정
//    스프링 컨테이너에 올라가는 것만 Autowired가 동작 즉 직접 생성하면 동작 하지 않음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
