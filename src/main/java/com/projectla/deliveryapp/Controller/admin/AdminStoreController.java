package com.projectla.deliveryapp.Controller.admin;

import com.projectla.deliveryapp.Dto.AdminStoreRequest;
import com.projectla.deliveryapp.Entity.Store;
import com.projectla.deliveryapp.Service.AdminStoreService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/stores")
public class AdminStoreController {

    private final AdminStoreService adminStoreService;

    public AdminStoreController(AdminStoreService adminStoreService) {
        this.adminStoreService = adminStoreService;
    }

    @PostMapping
    public Store createStore(@Valid @RequestBody AdminStoreRequest request) {
    return adminStoreService.createStore(request);
   }

    @PutMapping("/{id}")
    public Store updateStore(
            @PathVariable Long id,
            @RequestBody AdminStoreRequest request) {

        return adminStoreService.updateStore(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        adminStoreService.deleteStore(id);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return adminStoreService.getAllStores();
    }
}