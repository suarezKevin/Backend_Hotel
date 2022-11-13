package com.hotel.demo.repository;

import com.hotel.demo.enumerados.ListaRoles;
import com.hotel.demo.models.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository <Rol, Long>{

    Optional<Rol> findByTipo(ListaRoles tipo);
}
