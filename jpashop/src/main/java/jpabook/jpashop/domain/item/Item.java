package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 전략은 세 가지 있음
// 1. SINGLE_TABLE: 하나의 테이블에 모든 값을 넣는 것
// 2. JOINED: 가장 정규화된 방식으로 하는 것
// 3. TABLE_PER_CLASS: 세가지 테이블을 생성하는 것
@DiscriminatorColumn(name = "dtype")    // 테이블 구분을 위한 값
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
