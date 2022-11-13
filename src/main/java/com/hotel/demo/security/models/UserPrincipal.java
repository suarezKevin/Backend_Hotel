package com.hotel.demo.security.models;

import com.hotel.demo.models.entities.Usuario;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder

public class UserPrincipal implements UserDetails {

    private UUID id;
    private String username;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserPrincipal userToUserMain(Usuario user){

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_"
                                .concat(role.getTipo().name())
                        )).collect(Collectors.toList());
        return UserPrincipal.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(user.isActivo())
                .authorities(authorities)
                .build();
    }

}
