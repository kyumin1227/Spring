package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDto> index() {
        List<Pizza> pizzas = pizzaRepository.findAll();

        List<PizzaDto> pizzaDtos = pizzas.stream()
                .map(pizza -> PizzaDto.createDto(pizza))
                .collect(Collectors.toList());

        return pizzaDtos;
    }

    public PizzaDto showId(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("존재하지 않는 id 입니다."));
        return PizzaDto.createDto(pizza);
    }

    @Transactional
    public PizzaDto create(PizzaDto dto) {
        Pizza pizza = dto.toEntity();
        Pizza created = pizzaRepository.save(pizza);
        return PizzaDto.createDto(created);
    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto dto) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));
        if(target.getId() != id || id != dto.getId())
            throw new IllegalArgumentException("올바르지 않는 id 입니다.");
        target.patch(dto);
        Pizza updated = pizzaRepository.save(target);
        return PizzaDto.createDto(updated);
    }

    @Transactional
    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));
        pizzaRepository.delete(target);
        return PizzaDto.createDto(target);
    }
}
