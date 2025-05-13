package com.example.test_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMenuRequestDto {
    private String name;

    private Double price;

    private String description;

    private Long restaurantId;
}
