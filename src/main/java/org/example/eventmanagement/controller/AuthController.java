package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.AuthRequest;
import org.example.eventmanagement.entity.AuthResponse;
import org.example.eventmanagement.entity.User;
import org.example.eventmanagement.service.JwtService;
import org.example.eventmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        System.out.println("Encoding password for user: " + user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (!"USER".equals(user.getRole()) && !"ADMIN".equals(user.getRole())) {
            return ResponseEntity.badRequest().body("Invalid role");
        }

        userService.saveUser(user);
        System.out.println("User registered successfully: " + user.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userService.findUserByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Raw password: " + authRequest.getPassword());
        System.out.println("Encoded password: " + user.getPassword());

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String token = jwtService.generateToken(user.getId(), user.getRole());
        System.out.println("Generated token: " + token);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

