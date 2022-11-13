package com.hotel.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewUserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private List<String> roles = new ArrayList<>();

}
