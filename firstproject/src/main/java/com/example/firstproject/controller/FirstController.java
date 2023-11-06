package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "규민");
        return "greetings";     // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouAgain(Model model) {
        model.addAttribute("username", "규민");
        return "goodbye";
    }
}
