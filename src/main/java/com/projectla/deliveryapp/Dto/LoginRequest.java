package com.projectla.deliveryapp.Dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String phoneNumber;
    private String password;
}