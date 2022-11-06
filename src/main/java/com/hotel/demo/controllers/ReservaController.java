package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.ReservaDto;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import com.hotel.demo.services.HabitacionService;
import com.hotel.demo.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reservas")
//http://localhost:9095/hotel/reservas

public class ReservaController {

    private final ReservaService reservaService;
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaController(ReservaService reservaService, HabitacionService habitacionService) {
        this.reservaService = reservaService;
        this.habitacionService = habitacionService;
    }

    @PostMapping(value = "/{idhabitacion}")
    public ResponseEntity<?> addReserva(@PathVariable final Long idhabitacion, @RequestBody final ReservaDto reservaDto){
        if(!habitacionService.checkHabitacion(idhabitacion)){
            Reserva reserva = reservaService.addReserva(Reserva.from(reservaDto));
            Habitacion habitacion = habitacionService.changeEstadoHabitacion(idhabitacion);
            return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
        }
        return new ResponseEntity<>("La habitacion está ocupada", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getReservas(){
        List<Reserva> reservaList = reservaService.getReservas();
        List<ReservaDto> reservaDtoList = reservaList.stream().map(ReservaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(reservaDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> getReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.getReserva(idreserva);
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> deleteReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.deleteReserva(idreserva);
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

    @PutMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable final Long idreserva, @RequestBody final ReservaDto reservaDto){
        Reserva reserva = reservaService.updateReserva(idreserva, Reserva.from(reservaDto));
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

}
