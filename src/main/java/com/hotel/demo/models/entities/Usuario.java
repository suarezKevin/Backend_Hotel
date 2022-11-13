package com.hotel.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "contrasenia", nullable = false)
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(nullable = false)
    private boolean activo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_usuarios",
            joinColumns = {@JoinColumn (name="id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "id_rol")}
    )
    private List<Rol> roles;

    @PrePersist
    public void generateUUID(){
        activo = true;
    }

}
