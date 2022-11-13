package com.hotel.demo.models.entities;

import com.hotel.demo.enumerados.ListaRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Rol {

    @Id
    @Column(name = "id_rol")
    private Long id;

    public Rol(ListaRoles tipo) {
        this.tipo = tipo;
    }

    @Enumerated(EnumType.STRING)
    private ListaRoles tipo;

}
