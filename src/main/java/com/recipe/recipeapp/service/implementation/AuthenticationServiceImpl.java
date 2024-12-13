package com.hasthiyait.recipeapp.service.implementation;

import com.hasthiyait.recipeapp.dao.request.ChangePassword;
import com.hasthiyait.recipeapp.dao.request.SignInRequest;
import com.hasthiyait.recipeapp.dao.request.SignUpRequest;
import com.hasthiyait.recipeapp.dao.response.JwtAuthenticationResponse;
import com.hasthiyait.recipeapp.model.Role;
import com.hasthiyait.recipeapp.model.User;
import com.hasthiyait.recipeapp.repository.UserRepository;
import com.hasthiyait.recipeapp.service.AuthenticationService;
import com.hasthiyait.recipeapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Object signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).username(user.getUsername()).role(user.getRole()).build();
    }

    @Override
    public Object signUp(SignUpRequest request, Role role) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).username(user.getUsername()).role(user.getRole()).build();
    }

    @Override
    public String changePassword(ChangePassword request) {
        var user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            var _user = user.get();
            var oldPassword = request.getOldPassword();
            var newPassword = request.getNewPassword();
            if (passwordEncoder.matches(oldPassword, _user.getPassword())) {
                _user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(_user);
                return "Password changed successfully!";
            } else {
                return "Your current password is incorrect";
            }
        }
        return "Invalid username!";
    }
}
