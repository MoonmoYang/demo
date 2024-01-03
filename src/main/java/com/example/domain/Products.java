package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "PRODUCTS_NUM")
    private Long productsNum;

    @Column(name = "PRODUCTS_NAME")
    private String productsName;

    @Column(name = "PRODUCTS_PRICE")
    private Long productsPrice;

    @Column(name = "MAINCATEGORY_NUM")
    private Long maincategoryNum;

    @Column(name = "SUBCATEGORY_NUM")
    private Long subcategoryNum;



}
