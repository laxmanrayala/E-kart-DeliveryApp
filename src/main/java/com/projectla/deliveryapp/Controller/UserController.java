package com.projectla.deliveryapp.Controller;

import org.springframework.web.bind.annotation.*;

import com.projectla.deliveryapp.Entity.User;
import com.projectla.deliveryapp.Service.UserService;
import com.projectla.deliveryapp.security.SecurityUtil;

@RestController
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 GET LOGGED-IN USER PROFILE
    @GetMapping
    public User getProfile() {

        Long userId = SecurityUtil.getCurrentUserId();

        return userService.getUser(userId);
    }
}