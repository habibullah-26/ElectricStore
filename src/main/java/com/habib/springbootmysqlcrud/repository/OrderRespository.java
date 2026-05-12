package com.habib.springbootmysqlcrud.repository;

import com.habib.springbootmysqlcrud.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    Long countByUserId(Long userId);
}
