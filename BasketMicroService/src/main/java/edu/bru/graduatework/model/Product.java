package edu.bru.graduatework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "NUMBER_CALORIES")
    private Double numberCalories;

    @Column(name = "NUMBER_PROTEINS")
    private Double numberProteins;

    @Column(name = "NUMBER_FATS")
    private Double numberFats;

    @Column(name = "NUMBER_CARBOHYDRATES")
    private Double numberCarbohydrates;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IMG")
    private String img;
}
