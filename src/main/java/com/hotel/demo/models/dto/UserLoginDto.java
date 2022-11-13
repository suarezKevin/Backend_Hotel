package com.hotel.demo.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor

public class UserLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
