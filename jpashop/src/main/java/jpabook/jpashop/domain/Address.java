package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // 내장 가능
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 제약 때문에 기본 생성자 생성 하지만 PROTECTED로 설정하여 사용을 권장하지 않음
@AllArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
