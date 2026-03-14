package com.projectla.deliveryapp.Controller.admin;

import com.projectla.deliveryapp.Entity.Order;
import com.projectla.deliveryapp.Enum.OrderStatus;
import com.projectla.deliveryapp.Service.AdminOrderService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return adminOrderService.updateOrderStatus(id, status);
    }

    @GetMapping
    public Page<Order> getOrders(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    return adminOrderService.getOrders(page, size);
}
}