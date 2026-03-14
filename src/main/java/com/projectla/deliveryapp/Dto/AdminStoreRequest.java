package com.projectla.deliveryapp.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AdminStoreRequest {

    @NotBlank(message = "Store name is required")
    private String name;

    @NotBlank(message = "Store name is required")
    private String address;
    private String location;
}