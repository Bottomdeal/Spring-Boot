package com.example.test_back.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCreateRequestDto {
    @NotBlank(message = "메뉴명은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private String price;

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String description;
}
