package com.hotel.demo.models.entities;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.demo.models.dto.ReservaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
@Data

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", length = 15)
    private Long idreserva;

    @Column(name = "fecha_entrada", nullable = false)
    private Date fechaentrada;

    @Column(name = "fecha_salida", nullable = false)
    private Date fechasalida;

    @Column(name = "pago_habitacion", nullable = false)
    private Boolean pagohabitacion;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    public Cliente cliente;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    public Habitacion habitacion;

    @PrePersist
    public void prePersist(){
        fechaentrada = new Date();
    }

    public static Reserva from(ReservaDto reservaDto){
        Reserva reserva = new Reserva();
        reserva.setIdreserva(reservaDto.getIdreserva());
        reserva.setFechaentrada(reservaDto.getFechaentrada());
        reserva.setFechasalida(reservaDto.getFechasalida());
        reserva.setPagohabitacion(reservaDto.getPagohabitacion());
        reserva.setCliente(reservaDto.getCliente());
        reserva.setHabitacion(reservaDto.getHabitacion());
        return reserva;
    }
}
