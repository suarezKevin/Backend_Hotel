package com.hotel.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habitaciones")
@Data
//id, numero, estado, tipo, pago y precio
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion", length = 15)
    private Long id;

    @Column(name = "numero_habitacion", length = 15, nullable = false)
    private Integer numero;

    @Column(name = "estado_habitacion", nullable = false)
    private Boolean estado;

    @Column(name = "tipo_habitacion", length = 20, nullable = false)
    private String tipo;

    @Column(name = "precio_habitacion", length = 15, nullable = false)
    private Double precio;

}
