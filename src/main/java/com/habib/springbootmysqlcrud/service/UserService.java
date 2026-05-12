package com.habib.springbootmysqlcrud.service;

import com.habib.springbootmysqlcrud.entity.User;
import com.habib.springbootmysqlcrud.entity.dtos.UserDTO;
import com.habib.springbootmysqlcrud.exception.UserNotFoundException;
import com.habib.springbootmysqlcrud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = repo.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(int i= 0; i<userList.size();i++){
            User u1 = userList.get(i);
            UserDTO dto = new UserDTO();
            dto.setId(u1.getId());
            dto.setEmail(u1.getEmail());
            dto.setName(u1.getName());
            userDTOList.add(dto);
        }
        return userDTOList;
    }

    public User updateUser(User updateUser){
        User existingUer = repo.findById(updateUser.getId()).orElseThrow(()-> new UserNotFoundException("User not Found"));

        existingUer.setName(updateUser.getName());
        existingUer.setEmail(updateUser.getEmail());

        return repo.save(existingUer);
    }

    public String deleteUser(long id){
        User existingUser = repo.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));

        repo.delete(existingUser);
        return "User Deleted Successfully";
    }

    public User getUserByID(long id){
       return repo.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
    }
}