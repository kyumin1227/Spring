package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Controller 주석을 이용한 함수는 모두 Spring 컨테이너에 자동으로 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 의존 관계 설정
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
