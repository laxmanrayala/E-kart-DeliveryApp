package com.projectla.deliveryapp.Controller.admin;

import com.projectla.deliveryapp.Dto.AdminProductRequest;
import com.projectla.deliveryapp.Entity.Product;
import com.projectla.deliveryapp.Service.AdminProductService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping
    public Product createProduct(@RequestBody AdminProductRequest request) {
        return adminProductService.createProduct(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody AdminProductRequest request) {

        return adminProductService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
    }

    @PatchMapping("/{id}/stock")
    public Product updateStock(
            @PathVariable Long id,
            @RequestParam Integer stock) {

        return adminProductService.updateStock(id, stock);
    }

    @GetMapping
    public Page<Product> getProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    return adminProductService.getProducts(page, size);
}
}