package com.habib.springbootmysqlcrud.controller;

import com.habib.springbootmysqlcrud.entity.Product;
import com.habib.springbootmysqlcrud.response.ApiResponse;
import com.habib.springbootmysqlcrud.service.ProdcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProdcutService prodcutService;
    @PostMapping("save")
    public ApiResponse<List<Product>> create(@RequestBody Product product) {
        return prodcutService.createProduct(product);
    }

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        return prodcutService.getAllProducts();
    }
}