package com.cd.controller;

import com.cd.dto.LoginDto;
import com.cd.dto.UserInputDto;
import com.cd.dto.UserOutputDto;
import com.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserInputDto createUser (@RequestBody UserInputDto UserInputDto) {
        return userService.createUser (UserInputDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserOutputDto> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto)
                .map(userOutputDto -> ResponseEntity.ok().body(userOutputDto))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping
    public List<UserOutputDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(UserInputDto -> ResponseEntity.ok().body(UserInputDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInputDto> updateUser (@PathVariable int id, @RequestBody UserInputDto userDetails) {
        UserInputDto updatedUser  = userService.updateUser (id, userDetails);
        return updatedUser  != null ? ResponseEntity.ok().body(updatedUser ) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) {
        userService.deleteUser (id);
        return ResponseEntity.noContent().build();
    }
}