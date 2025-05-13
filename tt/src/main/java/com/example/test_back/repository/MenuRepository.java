package com.example.test_back.repository;

import com.example.test_back.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    static List<Menu> findByRestaurantId(Long restaurantId) {
        return List.of();
    }
}
