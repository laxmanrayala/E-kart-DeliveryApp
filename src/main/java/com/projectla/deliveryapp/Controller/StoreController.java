package com.projectla.deliveryapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.projectla.deliveryapp.Entity.Store;
import com.projectla.deliveryapp.Service.StoreService;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    // 🔹 CREATE STORE (ADMIN ONLY)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Store create(@RequestBody Store store) {
        return service.create(store);
    }

    // 🔹 GET STORE BY ID (PUBLIC)
    @GetMapping("{id}")
    public Store getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 🔹 GET ALL STORES (PUBLIC)
    @GetMapping
    public List<Store> getAllStores() {
        return service.getAllStores();
    }
}