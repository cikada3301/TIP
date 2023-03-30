package edu.bru.graduatework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

//    private final BasketService basketService;
//
//    @GetMapping
//    public ResponseEntity<BasketWithCaloriesDto> get() {
//        return ResponseEntity.ok(basketService.findBasketByUserId());
//    }
//
//    @PostMapping("/create-basket")
//    public ResponseEntity<Basket> add() {
//        basketService.add();
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> addProductToBasket(@RequestBody ProductDto product) {
//        basketService.addProductToBasket(product);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> delete() {
//        basketService.delete();
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
//        basketService.deleteProductById(id);
//        return ResponseEntity.ok().build();
//    }
}
