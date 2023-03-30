package edu.bru.graduatework.converter.impl;

import edu.bru.graduatework.converter.BasketWithCaloriesDtoConverter;
import edu.bru.graduatework.dto.BasketWithCaloriesDto;
import edu.bru.graduatework.model.Basket;
import edu.bru.graduatework.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BasketWithCaloriesDtoConverterImpl implements BasketWithCaloriesDtoConverter {
    @Override
    public BasketWithCaloriesDto convertFromDto(Basket basket) {

        return BasketWithCaloriesDto.builder()
                .numberOfProducts(basket.getNumberOfProducts())
                .user(basket.getUser())
                .price(basket.getPrice())
                .products(basket.getProducts())
                .build();
    }
}
