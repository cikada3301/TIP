package edu.bru.graduatework.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private String name;

    private String description;

    private String type;

    private Integer weight;

    private Double numberCalories;

    private Double numberProteins;

    private Double numberFats;

    private Double numberCarbohydrates;

    private BigDecimal price;
}
