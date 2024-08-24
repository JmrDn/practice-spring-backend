package com.codewithjmrdn.practice_spring_backend.controller;

import com.codewithjmrdn.practice_spring_backend.exception.UserNotFoundException;
import com.codewithjmrdn.practice_spring_backend.model.User;
import com.codewithjmrdn.practice_spring_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4000/", "https://practice-spring-frontend.vercel.app/", "http://127.0.0.1:8000/"})
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    String pingSpring(){
        return "Successfully Ping!";
    }
    @PostMapping("/user")
    User newUser (@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getUser(){return userRepository.findAll();}


    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Integer id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Integer id){
        if (!userRepository.existsById(id)){
            throw  new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User  with id " + id + " has been deleted successfully";
    }
}
