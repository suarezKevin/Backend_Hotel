package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Reserva;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

public class ReservaDto implements Serializable {

    private Long id;
    private Date fechaentrada;
    private Date fechasalida;
    private Boolean pagohabitacion;
    public Long cliente;
    public Long habitacion;

    public static ReservaDto from(Reserva reserva){
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setId(reserva.getId());
        reservaDto.setFechaentrada(reserva.getFechaentrada());
        reservaDto.setFechasalida(reserva.getFechasalida());
        reservaDto.setPagohabitacion(reserva.getPagohabitacion());
        reservaDto.setCliente(reserva.getCliente().getId());
        reservaDto.setHabitacion(reserva.getHabitacion().getId());
        return reservaDto;
    }

}
