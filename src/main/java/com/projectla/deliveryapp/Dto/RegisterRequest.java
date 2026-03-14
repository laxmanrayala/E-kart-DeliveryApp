package com.projectla.deliveryapp.Dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String phoneNumber;
    private String password;
}