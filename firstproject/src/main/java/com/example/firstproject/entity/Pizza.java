package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    public void patch(PizzaDto dto) {
        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getPrice() != null)
            this.price = dto.getPrice();
    }
}
