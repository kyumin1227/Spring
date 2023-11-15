package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null) {
            return null;
        }
        return coffee;
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getId() != null || coffee.getName() == null || coffee.getPrice() == 0) {
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee patch(Long id, CoffeeForm dto) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        Coffee coffee = dto.toEntity();
        if(target == null || id != coffee.getId()) {
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null) {
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
