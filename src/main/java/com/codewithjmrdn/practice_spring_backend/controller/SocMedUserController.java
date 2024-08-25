package com.codewithjmrdn.practice_spring_backend.controller;

import com.codewithjmrdn.practice_spring_backend.exception.UserNotFoundException;
import com.codewithjmrdn.practice_spring_backend.model.SocMedUser;
import com.codewithjmrdn.practice_spring_backend.repository.SocMedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4000/", "https://practice-spring-frontend.vercel.app/", "http://127.0.0.1:8000/"})
public class SocMedUserController {
    @Autowired
    private SocMedUserRepository socMedUserRepository;
    String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images";



    @PostMapping("/soc-med-user")
    public ResponseEntity<?> addUser(@ModelAttribute SocMedUser newUser, @RequestParam("image") MultipartFile file) throws IOException{

        String originalFileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFileName);
        Files.write(fileNameAndPath, file.getBytes());
        newUser.setImgName(originalFileName);


        Map<String, Object> map = new HashMap<>();
        map.put("details", "success");
        map.put("status", 200);
        map.put("data", socMedUserRepository.save(newUser));

        return ResponseEntity.ok(map);
    }
    @GetMapping("/soc-med-users")
    List<SocMedUser> getUser(){return socMedUserRepository.findAll();}


    @GetMapping("/soc-med-users/{id}")
    SocMedUser getUserById(@PathVariable Integer id){
        return socMedUserRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/soc-med-SocMedUsers/{id}")
    SocMedUser updateUser(@RequestBody SocMedUser newUser, @PathVariable Integer id){
        return socMedUserRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return socMedUserRepository.save(user);
                })
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/soc-med-users/{id}")
    String deleteUser(@PathVariable Integer id){
        if (!socMedUserRepository.existsById(id)){
            throw  new UserNotFoundException(id);
        }
        socMedUserRepository.deleteById(id);
        return "User  with id " + id + " has been deleted successfully";
    }


    @GetMapping("/getImages/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        // Access the image from the classpath
        Resource resource = new ClassPathResource("static/images/" + imageName);

        String fileType = "";
        int identifier = imageName.length() - 2;

        if (imageName.charAt(identifier) == 'n') fileType = "png";
        else fileType = "jpeg";

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/" + fileType); // Adjust the MIME type accordingly (e.g., "image/png" for PNG files)

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
