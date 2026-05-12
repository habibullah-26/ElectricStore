package com.habib.springbootmysqlcrud.service;

import com.habib.springbootmysqlcrud.entity.Product;
import com.habib.springbootmysqlcrud.repository.ProductRepository;
import com.habib.springbootmysqlcrud.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdcutService {

    private final ProductRepository productRepository;
    public ProdcutService(ProductRepository repo){
        this.productRepository = repo;
    }
    public ApiResponse<List<Product>> createProduct(Product product){
        Product product1 = productRepository.save(product);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        return new ApiResponse<>(200,"Product Successfully Created",productList);
    }

    public ApiResponse<List<Product>> getAllProducts(){
        return new ApiResponse<>(200,"success", productRepository.findAll());
    }
}
