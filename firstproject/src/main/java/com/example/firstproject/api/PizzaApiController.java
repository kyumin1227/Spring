package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> index() {
        List<PizzaDto> pizzaDtos = pizzaService.index();
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDtos);
    }

    @GetMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> showId(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.showId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDto);
    }

    @PostMapping("/api/pizzas")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto) {
        PizzaDto createdDto = pizzaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id,
                                           @RequestBody PizzaDto dto) {
        PizzaDto updetedDto = pizzaService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updetedDto);
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        PizzaDto deletedDto = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
