package edu.bru.graduatework.converter;

import edu.bru.graduatework.dto.BasketWithCaloriesDto;
import edu.bru.graduatework.model.Basket;

public interface BasketWithCaloriesDtoConverter {
    BasketWithCaloriesDto convertFromDto(Basket basket);
}
