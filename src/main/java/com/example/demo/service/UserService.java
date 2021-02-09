package com.example.demo.service;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service




public interface UserService {
    /**
     * Create a user
     * @param user the user to create without id and createdDate
     * @return created user
     */
    User addUser(User user);
    User updateUser(User user);
    User getUserById(Integer id) throws ResourceNotFoundException;
    List<User> getAllUser();
    void deleteUser(Integer id);
    String toString();

}
