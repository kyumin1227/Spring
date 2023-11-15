package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Coffee {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Integer price;

    public void patch(Coffee coffee) {
        if(coffee.name != null) {
            this.name = coffee.name;
        }
        if(coffee.price != 0) {
            this.price = coffee.price;
        }
    }

}
