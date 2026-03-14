package com.projectla.deliveryapp.Service;

import com.projectla.deliveryapp.Dto.AdminProductRequest;
import com.projectla.deliveryapp.Entity.Product;
import com.projectla.deliveryapp.Entity.Store;
import com.projectla.deliveryapp.Repository.ProductRepository;
import com.projectla.deliveryapp.Repository.StoreRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public AdminProductService(ProductRepository productRepository,
                               StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public Product createProduct(AdminProductRequest request) {

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setStore(store);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, AdminProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    public Product updateStock(Long id, Integer stock) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(stock);

        return productRepository.save(product);
    }

    public Page<Product> getProducts(int page, int size) {

    return productRepository.findAll(PageRequest.of(page, size));
   }
}