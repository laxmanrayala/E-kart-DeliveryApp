package com.projectla.deliveryapp.Dto;

import lombok.Data;

@Data
public class AdminProductRequest {

    private String name;
    private String description;
    private Double price;
    private Long storeId;
    private Integer stock;
    private String imageUrl;
}