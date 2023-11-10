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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {     // 매개변수로 id 받아 오기
        log.info("id: " + id);
//        1. id를 조회해 데이터 가져오기 (두 가지 방법)
//        Optional<Article> articleEntity = articleRepository.findById(id);   // findById의 반환값은 Optional
        Article articleEntity = articleRepository.findById(id).orElse(null);    // 값이 없으면 null 반환
//        2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
//        3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
//        1. 모든 데이터 가져오기 (세 가지 방법)
//        List<Article> articlesEntityList = (List<Article>) articleRepository.findAll();     // findAll은 Iterable을 반환하기 때문에 List<Article>로 캐스팅
//        Iterable<Article> articlesEntityList = articleRepository.findAll();       // findAll을 Iterable로 받는 방법
        List<Article> articlesEntityList = articleRepository.findAll();     // articleRepository에서 findAll 함수의 반환값을 ArrayList로 수정
//        2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articlesEntityList);
//        3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
//        수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
//        모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
//        뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("articles/update")     // HTML로 하기 위해 임시로 POST 메소드로 받음
    public String update(ArticleForm form) {
        log.info(form.toString());
//        1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
//        2. 엔티티를 DB에 저장하기
//        2-1. 수정을 위해 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
//        2-2. 기존 데이터 값을 갱신하기
        if(target != null) {
            articleRepository.save(articleEntity);
        }
//        3. 수정 결과 페이지로 리다이렉트 하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")    // HTML로 하기 위해 임시로 get 메소드로 받음
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {      // redirect 시 보낼 메세지
        log.info("삭제 요청이 들어왔습니다!!");
//        삭제할 데이터 가져오기
        Article target = articleRepository.findById(id).orElse(null);
//        데이터가 있을 경우 삭제
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");   // redirect 시 보낼 메세지
        }
        return "redirect:/articles";
    }
}
