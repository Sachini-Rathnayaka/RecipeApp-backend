package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<List<User>> response = new ApiResponse<>(status, userService.getAllUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserById(@PathVariable Long id) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Successfully fetched!");
        ApiResponse<Optional<User>> response = new ApiResponse<>(status, userService.getUserById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
