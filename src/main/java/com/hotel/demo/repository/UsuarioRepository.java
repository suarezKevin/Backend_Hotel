package com.hotel.demo.repository;

import com.hotel.demo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);

}
