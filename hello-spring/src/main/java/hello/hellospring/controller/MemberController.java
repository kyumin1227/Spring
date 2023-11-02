package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Controller 주석을 이용한 함수는 모두 Spring 컨테이너에 자동으로 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 의존 관계 설정
//    스프링 컨테이너에 올라가는 것만 Autowired가 동작 즉 직접 생성하면 동작 하지 않음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

//    MemberForm으로 값을 받고있기 때문에 MemberForm을 호출하여 formData를 받은 이때
//    formData로 넘어온 값의 key가 name이기 때문에 Spring이 MemberForm의 name에 setName함수를 활용하여 자동으로 값을 넣어 줌
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);  // html에 넘기는 속성
        return "members/memberList";
    }

}
