package com.projectla.deliveryapp.Controller;

import com.projectla.deliveryapp.Dto.OrderResponse;
import com.projectla.deliveryapp.Enum.OrderStatus;
import com.projectla.deliveryapp.Service.OrderService;
import com.projectla.deliveryapp.security.SecurityUtil;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 🔹 CHECKOUT
    @PostMapping("/checkout")
    public OrderResponse placeOrder() {

        Long userId = SecurityUtil.getCurrentUserId();   // ⭐ NEW

        return orderService.placeOrder(userId);
    }

    // 🔹 GET ALL ORDERS
    @GetMapping
    public List<OrderResponse> getOrders() {

        Long userId = SecurityUtil.getCurrentUserId();   // ⭐ NEW

        return orderService.getOrders(userId);
    }

    // 🔹 GET SINGLE ORDER
    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {

        Long userId = SecurityUtil.getCurrentUserId();   // ⭐ NEW

        return orderService.getOrderById(userId, orderId);
    }

    // 🔹 UPDATE STATUS
    @PatchMapping("/{orderId}/status")
    public OrderResponse updateStatus(@PathVariable Long orderId,
                                      @RequestParam OrderStatus status) {

        Long userId = SecurityUtil.getCurrentUserId();   // ⭐ NEW

        return orderService.updateOrderStatus(userId, orderId, status);
    }
}