package com.GraduateWork.ProductMicroService.repository;

import com.GraduateWork.ProductMicroService.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
