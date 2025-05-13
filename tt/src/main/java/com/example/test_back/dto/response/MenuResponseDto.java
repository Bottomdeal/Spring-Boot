package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class MenuResponseDto {
    private Long id;
    private Long restaurantId;

    private final String name;
    private final String description;

    private Double price;

    public static class Builder {
        private Long id;
        private Long restaurantId;
        private Double price;
        private final String name;
        private final String description;

        public Builder(String name, Double price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public MenuResponseDto build() {
            return new MenuResponseDto(id, restaurantId, name, description, price);
        }
    }
}
