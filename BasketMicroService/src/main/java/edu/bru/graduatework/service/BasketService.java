package edu.bru.graduatework.service;


import edu.bru.graduatework.dto.BasketWithCaloriesDto;

public interface BasketService {
    BasketWithCaloriesDto findBasketByUserId(String username);

    void add(String username);

    void addProductToBasket(Long productId, String username);

    void delete(String username);

    void deleteProductById(Long id, String username);
}
