package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.MenuCreateRequestDto;
import com.example.test_back.dto.request.MenuUpdateRequestDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository = null;
    private final RestaurantRepository restaurantRepository = null;

    @Override
    @Transactional(readOnly = false)
    public ResponseDto<MenuResponseDto> createMenu(Long restaurantId, @Valid MenuCreateRequestDto dto) {
        MenuResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + restaurantId));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .price(Double.valueOf(dto.getPrice()))
                .description(dto.getDescription())
                .build();

        restaurant.addMenu(newMenu); // D_Comment가 D_Post에 추가되고 동시에 post 필드가 설정

        Menu savedMenu = menuRepository.save(newMenu);


        responseDto = new MenuResponseDto.Builder(savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription())
                .id(savedMenu.getId())
                .restaurantId(savedMenu.getRestaurant().getId())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<List<MenuResponseDto>> getMenuByRestaurantId(Long restaurantId) {
        List<MenuResponseDto> responseDtos = null;

        Menu menu = menuRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT + restaurantId));

        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            // 2번 게시물, 3번 댓글 수정
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        List<Menu> menus = MenuRepository.findByRestaurantId(restaurantId);

        responseDtos = menus.stream()
                .map(post -> MenuResponseDto.builder()
                        .id(post.getId())
                        .name(post.getName())
                        .price(post.getPrice())
                        .description(post.getDescription())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, Collections.singletonList((MenuResponseDto) responseDtos));
    }

    @Override
    public ResponseDto<List<MenuResponseDto>> getMenuById(Long menuId) {
        PostMenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + menuId));


        responseDto = PostMenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, (List<MenuResponseDto>) responseDto);
    }

    @Override
    public ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, @Valid MenuUpdateRequestDto dto) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT + menuId));

        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            // 2번 게시물, 3번 댓글 수정
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        menu.setName(dto.getName());
        menu.setPrice(Double.valueOf(dto.getPrice()));
        menu.setDescription(dto.getDescription());
        Menu updatedMenu = menuRepository.save(menu);

        responseDto = MenuResponseDto.builder()
                .id(updatedMenu.getId())
                .restaurantId(updatedMenu.getRestaurant().getId())
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .description(updatedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT + menuId));

        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        // == 연관 관계를 해제 == //
        menu.getRestaurant().removeMenu(menu);

        // == DB에서 삭제 == //
        menuRepository.delete(menu);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

