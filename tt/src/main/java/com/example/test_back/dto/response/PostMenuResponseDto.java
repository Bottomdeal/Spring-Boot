package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostMenuResponseDto {
    private Long id;

    private String name;

    private Double price;

    private String description;

    private List<RestaurantResponseDto> restaurant;
}
