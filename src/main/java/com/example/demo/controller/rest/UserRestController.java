package com.example.demo.controller.rest;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/api/correction")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public User createUser( @Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity < User > updateUser(@Valid @PathVariable(value = "id") Integer userId,
                                              @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));


        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setAd(userDetails.getAd());
        user.setEmail(userDetails.getEmail());
        user.setEmail(userDetails.getPwd());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Map< String, Boolean > deleteUser(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/listUser")
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/infoUser/{id}")
    public ResponseEntity< User > getUserById(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }



}
