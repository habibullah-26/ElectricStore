package com.habib.springbootmysqlcrud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Double price;
    private String detail;
}
