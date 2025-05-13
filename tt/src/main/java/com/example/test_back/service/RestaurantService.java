package com.example.test_back.service;

import com.example.test_back.dto.request.PostCreateRestaurantRequestDto;
import com.example.test_back.dto.request.RestaurantUpdateRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface RestaurantService {
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(@Valid PostCreateRestaurantRequestDto dto);
    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);
    ResponseDto<List<PostRestaurantResponseDto>> getAllRestaurants();
    ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, @Valid RestaurantUpdateRequestDto dto);
    ResponseDto<Void> deleteRestaurant(Long id);

}
