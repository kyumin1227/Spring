package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     // 롬복을 이용하여 생성자 코드 대체
@ToString               // 롬복을 이용하여 toString 코드 대체
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
