package com.example.test_back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<>();

    public void addMenu(Menu newMenu) {
        this.menus.add(newMenu);
        newMenu.setRestaurant(this);
    }

    public void removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.setRestaurant(null); // Menu 객체의 연관 관계 해제
    }
}
