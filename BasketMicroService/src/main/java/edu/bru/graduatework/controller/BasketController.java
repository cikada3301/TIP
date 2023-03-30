package edu.bru.graduatework.controller;

import edu.bru.graduatework.dto.BasketWithCaloriesDto;
import edu.bru.graduatework.model.Basket;
import edu.bru.graduatework.service.BasketService;
import edu.bru.graduatework.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<BasketWithCaloriesDto> get(HttpServletRequest request) {
        return ResponseEntity.ok(basketService.findBasketByUserId(extrectUserName(request)));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Basket> add(@PathVariable String username) {
        basketService.add(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addProductToBasket(@PathVariable Long productId, HttpServletRequest request) {
        basketService.addProductToBasket(productId, extrectUserName(request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(HttpServletRequest request) {
        basketService.delete(extrectUserName(request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id, HttpServletRequest request) {
        basketService.deleteProductById(id, extrectUserName(request));
        return ResponseEntity.ok().build();
    }

    private String extrectUserName(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        return username;
    }
}
