package com.GraduateWork.ProductMicroService.service.impl;

import com.GraduateWork.ProductMicroService.model.Product;
import com.GraduateWork.ProductMicroService.repository.ProductRepository;
import com.GraduateWork.ProductMicroService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> get() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public List<Product> getByName(String name) {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream()
                .filter(product -> product.getName().startsWith(name))
                .collect(Collectors.toList());
    }
}
