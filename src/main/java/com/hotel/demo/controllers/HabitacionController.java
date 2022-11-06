package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.HabitacionDto;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/habitaciones")
//http://localhost:9095/hotel/habitaciones

public class HabitacionController {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping
    public ResponseEntity<HabitacionDto> addHabitacion(@RequestBody final HabitacionDto habitacionDto){
        Habitacion habitacion = habitacionService.addHabitacion(Habitacion.from(habitacionDto));
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDto>> getHabitaciones(){
        List<Habitacion> habitaciones = habitacionService.getHabitaciones();
        List<HabitacionDto> habitacionDtos = habitaciones.stream().map(HabitacionDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(habitacionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> getHabitacion(@PathVariable final Long idhabitacion){
        Habitacion habitacion = habitacionService.getHabitacion(idhabitacion);
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> deleteHabitacion(@PathVariable final Long idhabitacion){
        Habitacion habitacion = habitacionService.deleteHabitacion(idhabitacion);
        return new ResponseEntity<>(HabitacionDto.from(habitacion), HttpStatus.OK);
    }

    @PutMapping(value = "/{idhabitacion}")
    public ResponseEntity<HabitacionDto> updateHabitacion(@PathVariable final Long idhabitacion, @RequestBody final HabitacionDto habitacionDto){
        Habitacion habitacion = habitacionService.updateHabitacion(idhabitacion, Habitacion.from(habitacionDto));
        return new ResponseEntity<>(HabitacionDto.from(habitacion),HttpStatus.OK);
    }

}
