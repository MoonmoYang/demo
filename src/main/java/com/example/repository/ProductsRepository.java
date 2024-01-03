package com.example.repository;

import com.example.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

   Optional<Products> findByProductsName(String pdName);

    @Override
    List<Products> findAll();







}