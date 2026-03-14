package com.projectla.deliveryapp.Controller;

import com.projectla.deliveryapp.Dto.AddToCartRequest;
import com.projectla.deliveryapp.Dto.CartResponse;
import com.projectla.deliveryapp.Service.CartService;
import com.projectla.deliveryapp.security.SecurityUtil;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody AddToCartRequest request) {

        Long userId = SecurityUtil.getCurrentUserId();

        return cartService.addToCart(
                userId,
                request.getProductId(),
                request.getQuantity()
        );
    }

    @GetMapping
    public CartResponse getCart() {

        Long userId = SecurityUtil.getCurrentUserId();

        return cartService.getCart(userId);
    }

    @PatchMapping("/reduce")
    public CartResponse reduceQuantity(@RequestBody AddToCartRequest request) {

        Long userId = SecurityUtil.getCurrentUserId();

        return cartService.reduceQuantity(
                userId,
                request.getProductId(),
                request.getQuantity()
        );
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponse removeItem(@PathVariable Long productId) {

        Long userId = SecurityUtil.getCurrentUserId();

        return cartService.removeItem(userId, productId);
    }

    @DeleteMapping("/clear")
    public CartResponse clearCart() {

        Long userId = SecurityUtil.getCurrentUserId();

        return cartService.clearCart(userId);
    }


 

}
