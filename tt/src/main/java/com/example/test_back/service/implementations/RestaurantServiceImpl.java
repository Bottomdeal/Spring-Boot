package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.PostCreateRestaurantRequestDto;
import com.example.test_back.dto.request.RestaurantUpdateRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository = null;
    @Override
    @Transactional
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(@Valid PostCreateRestaurantRequestDto dto) {
        PostRestaurantResponseDto responseDto = null;
        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant saved = restaurantRepository.save(newRestaurant);

        responseDto = PostRestaurantResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);

    }


    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        responseDto = RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostRestaurantResponseDto>> getAllRestaurants() {
        List<PostRestaurantResponseDto> responseDtos = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        responseDtos = restaurants.stream()
                .map(restaurant -> PostRestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    @Transactional
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, RestaurantUpdateRequestDto dto) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(updatedRestaurant.getId())
                .name(updatedRestaurant.getName())
                .address(updatedRestaurant.getAddress())
                .phoneNumber(updatedRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        restaurantRepository.deleteById(id);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
