package com.projectla.deliveryapp.Controller;

import com.projectla.deliveryapp.Dto.*;
import com.projectla.deliveryapp.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔹 REGISTER USER
    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {

        authService.register(request);

        // Automatically login after register
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber(request.getPhoneNumber());
        loginRequest.setPassword(request.getPassword());

        return authService.login(loginRequest);
    }

    // 🔹 LOGIN USER
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}