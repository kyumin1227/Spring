package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity     // 엔티티 선언
@AllArgsConstructor     // 롬복을 이용하여 생성자 대체
@NoArgsConstructor      // 롬복을 이용하여 기본 생성자 대체
@ToString               // 롬복을 이용하여 toString 대체
public class Article {

    @Getter
    @Id     // 엔티티의 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // DB가 id 자동 생성
    private Long id;
    @Getter     // api/article에서 값을 못 받아와서 임시로 추가
    @Column
    private String title;
    @Getter     // api/article에서 값을 못 받아와서 임시로 추가
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }
        if(article.content != null) {
            this.content = article.content;
        }
    }

    //    public Article() {
//
//    }

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
