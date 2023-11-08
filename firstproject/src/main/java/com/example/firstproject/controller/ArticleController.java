package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j      // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {

    @Autowired  // 스프링 부트가 자동으로 객체 주입
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());
//        1. DTO를 엔티티로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());
//        2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {     // 매개변수로 id 받아 오기
        log.info("id: " + id);
//        1. id를 조회해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);   // findById의 반환값은 Optional
        Article articleEntity = articleRepository.findById(id).orElse(null);    // 값이 없으면 null 반환
//        2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
//        3. 뷰 페이지 반환하기
        return "articles/show";
    }
}
