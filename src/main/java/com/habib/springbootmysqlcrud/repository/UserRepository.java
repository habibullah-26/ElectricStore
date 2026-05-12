package com.habib.springbootmysqlcrud.repository;

import com.habib.springbootmysqlcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}