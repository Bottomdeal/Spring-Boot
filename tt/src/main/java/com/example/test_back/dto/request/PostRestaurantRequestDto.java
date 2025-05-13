package com.example.test_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRestaurantRequestDto {
    private String name;

    private String address;

    private String phoneNumber;
}
