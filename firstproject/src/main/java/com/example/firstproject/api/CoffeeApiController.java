package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getName() == null) {
            log.info("이름이 null입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(coffee.getPrice().equals(0)) {
            log.info("가격이 0입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(coffee);
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> patch(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != coffee.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
