package com.example.web;

import com.example.domain.Products;
import com.example.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productsService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Optional<Products> product = productsService.findProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> getProductByName(@RequestParam String name) {
        Optional<Products> product = productsService.findProductByName(name);
        return product.map(p -> ResponseEntity.ok(List.of(p)))
                .orElseGet(() -> ResponseEntity.ok(List.of()));
    }

    @GetMapping("/category/{mainCategory}")
    public ResponseEntity<List<Products>> getProductsByMainCategory(@PathVariable Long mainCategory) {
        List<Products> products = productsService.findProductsByMainCategory(mainCategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/subcategory/{subCategory}")
    public ResponseEntity<List<Products>> getProductsBySubCategory(@PathVariable Long subCategory) {
        List<Products> products = productsService.findProductsBySubCategory(subCategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{mainCategory}/subcategory/{subCategory}")
    public ResponseEntity<List<Products>> getProductsByMainCategoryForSubcategory(@PathVariable Long mainCategory, @PathVariable Long subCategory) {
        List<Products> products = productsService.findProductsByMainCategoryForSubcategory(mainCategory, subCategory);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products savedProduct = productsService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}
