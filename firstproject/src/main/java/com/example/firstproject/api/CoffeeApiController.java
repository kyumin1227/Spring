package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.service.CoffeeService;
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
    private CoffeeService coffeeService;

//    @Autowired
//    private CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> patch(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee patched = coffeeService.patch(id, dto);
        return (patched != null) ?
                ResponseEntity.status(HttpStatus.OK).body(patched) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
