package com.hotel.demo.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JwtResponse {

    private String token;
    private final String TOKEN_HEADER ="Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

}
