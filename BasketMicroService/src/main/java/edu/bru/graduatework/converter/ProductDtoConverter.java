package edu.bru.graduatework.converter;

import edu.bru.graduatework.dto.ProductDto;
import edu.bru.graduatework.model.Product;

public interface ProductDtoConverter {
    Product convertFromDto(ProductDto productDto);
}
