package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.PostCreateRestaurantRequestDto;
import com.example.test_back.dto.request.RestaurantUpdateRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // ResponseBody + Controller
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
public class RestaurantController {
    @Autowired // 의존성을 자동 주입 - 필드 주입
    private RestaurantService restaurantService;

    // 1) 레스토랑 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostRestaurantResponseDto>> createRestaurant(@Valid @RequestBody PostCreateRestaurantRequestDto dto) {
        ResponseDto<PostRestaurantResponseDto> restaurant = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    // 2) 단건 조회
    @GetMapping("/{id}") //     "/api/v1/restaurants/{id}"
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // 3) 전체 조회 (메뉴 제외)
    @GetMapping
    public ResponseEntity<ResponseDto<List<PostRestaurantResponseDto>>> getAllRestaurants() {
        ResponseDto<List<PostRestaurantResponseDto>> posts = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 4) 레스토랑 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(
            @PathVariable Long id, @Valid @RequestBody RestaurantUpdateRequestDto dto
    ) {
        ResponseDto<RestaurantResponseDto> response = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 5) 레스토랑 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }















}
