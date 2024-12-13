package com.hasthiyait.recipeapp.controller;

import com.hasthiyait.recipeapp.dao.request.ChangePassword;
import com.hasthiyait.recipeapp.dao.request.SignInRequest;
import com.hasthiyait.recipeapp.dao.request.SignUpRequest;
import com.hasthiyait.recipeapp.dao.response.ApiResponse;
import com.hasthiyait.recipeapp.dao.response.JwtAuthenticationResponse;
import com.hasthiyait.recipeapp.model.Role;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<Object>> signIn(@RequestBody SignInRequest request) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "Login Successfully!");
        ApiResponse<Object> response = new ApiResponse<>(status, authenticationService.signIn(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Object>> signUp(@RequestBody SignUpRequest request) {
        Role role = Role.valueOf(request.getRole());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            ApiResponse.Status status = new ApiResponse.Status(HttpStatus.BAD_REQUEST.value(), "This mail is already used!");
            ApiResponse<Object> response = new ApiResponse<>(status, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.CREATED.value(), "Registration Successfully!");
        ApiResponse<Object> response = new ApiResponse<>(status, authenticationService.signUp(request, role));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestBody ChangePassword request) {
        ApiResponse.Status status = new ApiResponse.Status(HttpStatus.OK.value(), "OK");
        ApiResponse<String> response = new ApiResponse<>(status, authenticationService.changePassword(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
