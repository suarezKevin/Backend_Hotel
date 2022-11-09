package com.hotel.demo.models.dto;

import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data

public class ReservaDto implements Serializable {

    private Long idreserva;
    private Date fechaentrada;
    private Date fechasalida;
    private Boolean pagohabitacion;
    public Long clienteid;
    public Long habitacionid;

    public static ReservaDto from(Reserva reserva){
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setIdreserva(reserva.getIdreserva());
        reservaDto.setFechaentrada(reserva.getFechaentrada());
        reservaDto.setFechasalida(reserva.getFechasalida());
        reservaDto.setPagohabitacion(reserva.getPagohabitacion());
        reservaDto.setClienteid(reserva.getCliente().getId());
        reservaDto.setHabitacionid(reserva.getHabitacion().getId());
        return reservaDto;
    }

}
