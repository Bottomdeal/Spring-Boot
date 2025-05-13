package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.MenuCreateRequestDto;
import com.example.test_back.dto.request.MenuUpdateRequestDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    @Autowired
    private final MenuService menuService;

    // 1) 메뉴 생성
    @PostMapping
    public ResponseEntity<ResponseDto<MenuResponseDto>> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuCreateRequestDto dto
    ) {
        ResponseDto<MenuResponseDto> response = menuService.createMenu(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 단건 조회(메뉴Id)
    @GetMapping("/{menuId}")
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getMenuById(@PathVariable Long id) {
        ResponseDto<List<MenuResponseDto>> menu = menuService.getMenuById(id);
        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    // 3) 단건 조회(레스토랑Id)
    @GetMapping
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getMenuByRestaurantId(@PathVariable Long id) {
        ResponseDto<List<MenuResponseDto>> menus = menuService.getMenuByRestaurantId(id);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    // 4) 메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuUpdateRequestDto dto
    ) {
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 5) 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId
    ) {
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }





}
