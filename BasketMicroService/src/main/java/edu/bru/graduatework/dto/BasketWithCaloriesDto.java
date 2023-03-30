package edu.bru.graduatework.dto;

import edu.bru.graduatework.model.Product;
import edu.bru.graduatework.model.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class BasketWithCaloriesDto {
    private Integer numberOfProducts;

    private User user;

    private List<Product> products;

    private BigDecimal price;

    private Double numberCalories;

    private Double numberProteins;

    private Double numberFats;

    private Double numberCarbohydrates;
}
