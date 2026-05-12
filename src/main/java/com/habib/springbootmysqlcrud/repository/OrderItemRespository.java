package com.habib.springbootmysqlcrud.repository;

import com.habib.springbootmysqlcrud.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRespository extends JpaRepository<OrderItem, Long> {
}
