package com.example.test_back.service;

import com.example.test_back.dto.request.MenuCreateRequestDto;
import com.example.test_back.dto.request.MenuUpdateRequestDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    ResponseDto<MenuResponseDto> createMenu(Long menuId, @Valid MenuCreateRequestDto dto);
    ResponseDto<List<MenuResponseDto>> getMenuByRestaurantId(Long restaurantId);
    ResponseDto<List<MenuResponseDto>> getMenuById(Long menuId);
    ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, @Valid MenuUpdateRequestDto dto);

    ResponseDto<Void> deleteMenu(Long postId, Long commentId);
}
