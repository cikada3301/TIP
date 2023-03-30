package com.GraduateWork.ProductMicroService.service;

import com.GraduateWork.ProductMicroService.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> get();
    List<Product> getByName(String name);
}
