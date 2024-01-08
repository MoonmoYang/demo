package com.example.service;

import com.example.domain.Products;
import com.example.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> findAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Products> findProductById(Long id) {
        return productsRepository.findById(id);
    }

    public Optional<Products> findProductByName(String name) {
        return productsRepository.findByProductsName(name);
    }

    public List<Products> findProductsByMainCategory(Long mainCategoryNum) {
        return productsRepository.findByMaincategoryNum(mainCategoryNum);
    }

    public List<Products> findProductsBySubCategory(Long subCategoryNum) {
        return productsRepository.findBySubcategoryNum(subCategoryNum);
    }

    public List<Products> findProductsByMainCategoryForSubcategory(Long mainCategoryNum, Long subCategoryNum) {
        return productsRepository.findByMaincategoryNumAndSubcategoryNum(mainCategoryNum, subCategoryNum);
    }

    public Products saveProduct(Products products) {
        return productsRepository.save(products);
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    // Additional business logic and interactions with the repository can go here
}
