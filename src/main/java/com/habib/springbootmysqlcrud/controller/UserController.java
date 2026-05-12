package com.habib.springbootmysqlcrud.controller;

import com.habib.springbootmysqlcrud.entity.User;
import com.habib.springbootmysqlcrud.entity.dtos.UserDTO;
import com.habib.springbootmysqlcrud.response.ApiResponse;
import com.habib.springbootmysqlcrud.service.UserService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    public ApiResponse<List<UserDTO>> getUsers() {
        List<UserDTO> users = service.getAllUsers();
        return new ApiResponse<>(200,"fetched successfully", users);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return service.getUserByID(id);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        return service.deleteUser(id);
    }
}