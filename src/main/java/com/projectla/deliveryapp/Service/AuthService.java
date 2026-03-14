package com.projectla.deliveryapp.Service;

import com.projectla.deliveryapp.Dto.*;
import com.projectla.deliveryapp.Entity.User;
import com.projectla.deliveryapp.Enum.Role;
import com.projectla.deliveryapp.Repository.UserRepository;
import com.projectla.deliveryapp.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;    
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("Phone number already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // default role

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

    User user = userRepository
            .findByPhoneNumber(request.getPhoneNumber())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtUtil.generateToken(
            user.getId(),
            user.getRole().name()
    );

    return new LoginResponse(
            token,
            user.getId(),
            user.getRole().name()
    );
}
}