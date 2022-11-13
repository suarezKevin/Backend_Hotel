package com.hotel.demo.services;

import com.hotel.demo.models.entities.Usuario;
import com.hotel.demo.repository.UsuarioRepository;
import com.hotel.demo.security.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(userName).orElseThrow(
                ()-> new UsernameNotFoundException("User NOT found")
        );
        return UserPrincipal.userToUserMain(usuario);
    }

}
