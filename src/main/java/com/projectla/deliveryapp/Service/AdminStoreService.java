package com.projectla.deliveryapp.Service;

import com.projectla.deliveryapp.Dto.AdminStoreRequest;
import com.projectla.deliveryapp.Entity.Store;
import com.projectla.deliveryapp.Repository.StoreRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminStoreService {

    private final StoreRepository storeRepository;

    public AdminStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store createStore(AdminStoreRequest request) {

        Store store = new Store();
        store.setName(request.getName());
        store.setAddress(request.getAddress());
        store.setLocation(request.getLocation());

        return storeRepository.save(store);
    }

    public Store updateStore(Long id, AdminStoreRequest request) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setName(request.getName());
        store.setAddress(request.getAddress());
        store.setLocation(request.getLocation());

        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        storeRepository.delete(store);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}