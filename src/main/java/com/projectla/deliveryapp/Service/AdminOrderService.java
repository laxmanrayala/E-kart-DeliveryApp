package com.projectla.deliveryapp.Service;

import com.projectla.deliveryapp.Entity.Order;
import com.projectla.deliveryapp.Enum.OrderStatus;
import com.projectla.deliveryapp.Repository.OrderRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderService {

    private final OrderRepository orderRepository;

    public AdminOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }


    public Page<Order> getOrders(int page, int size) {

    return orderRepository.findAll(PageRequest.of(page, size));
   }
}