package edu.bru.graduatework.dto;

import edu.bru.graduatework.model.Product;
import edu.bru.graduatework.model.User;

import java.math.BigDecimal;
import java.util.List;

public class OrderWithCaloriesDto {
    private Integer numberOfProducts;

    private User user;

    private List<Product> products;

    private BigDecimal price;

    private Double numberCalories;

    private Double numberProteins;

    private Double numberFats;

    private Double numberCarbohydrates;
}
