package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PizzaDto {

    private Long id;
    private String name;
    private Integer price;


    public static PizzaDto createDto(Pizza pizza) {
        return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice());
    }


    public Pizza toEntity() {
        return new Pizza(this.id, this.name, this.price);
    }
}
