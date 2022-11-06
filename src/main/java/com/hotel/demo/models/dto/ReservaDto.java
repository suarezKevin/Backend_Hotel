package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data

public class ReservaDto {

    private Long idreserva;
    private Date fechaentrada;
    private Date fechasalida;
    private Boolean pagohabitacion;
    public Cliente cliente;
    public Habitacion habitacion;

    public static ReservaDto from(Reserva reserva){
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setIdreserva(reserva.getIdreserva());
        reservaDto.setFechaentrada(reserva.getFechaentrada());
        reservaDto.setFechasalida(reserva.getFechasalida());
        reservaDto.setPagohabitacion(reserva.getPagohabitacion());
        //reservaDto.setCliente(reserva.getCliente());
        //reservaDto.setHabitacion(reserva.getHabitacion());
        return reservaDto;
    }

}