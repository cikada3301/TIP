package edu.bru.graduatework.converter.impl;

import edu.bru.graduatework.converter.ProductDtoConverter;
import edu.bru.graduatework.dto.ProductDto;
import edu.bru.graduatework.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverterImpl implements ProductDtoConverter {
    @Override
    public Product convertFromDto(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .type(productDto.getType())
                .weight(productDto.getWeight())
                .numberCalories(productDto.getNumberCalories())
                .numberProteins(productDto.getNumberProteins())
                .numberCarbohydrates(productDto.getNumberCarbohydrates())
                .numberFats(productDto.getNumberFats())
                .price(productDto.getPrice())
                .build();
    }
}
