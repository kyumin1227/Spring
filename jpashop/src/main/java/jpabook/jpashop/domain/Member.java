package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")     // 콜룸 이름 지정
    private Long id;

    private String name;

    @Embedded   // 내장 타입 포함
    private Address address;

    @OneToMany(mappedBy = "member")  // 연관관계의 주인이 아니기 때문에 mappedBy 추가
    private List<Order> orders = new ArrayList<>();

}
