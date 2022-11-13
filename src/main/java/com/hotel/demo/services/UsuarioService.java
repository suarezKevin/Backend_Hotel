package com.hotel.demo.services;

import com.hotel.demo.models.entities.Usuario;
import com.hotel.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByUsername(nombreUsuario);
    }

    public boolean existByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByUsername(nombreUsuario);
    }

    public void saveUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }


}
