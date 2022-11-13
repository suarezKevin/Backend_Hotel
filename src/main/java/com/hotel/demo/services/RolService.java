package com.hotel.demo.services;

import com.hotel.demo.enumerados.ListaRoles;
import com.hotel.demo.models.entities.Rol;
import com.hotel.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional

public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol getByNombreRol(ListaRoles nombreRol){
        return rolRepository.findByTipo(nombreRol).get();
    }

}
