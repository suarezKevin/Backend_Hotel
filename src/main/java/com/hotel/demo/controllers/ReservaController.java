package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.ReservaDto;
import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.models.entities.Habitacion;
import com.hotel.demo.models.entities.Reserva;
import com.hotel.demo.services.HabitacionService;
import com.hotel.demo.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Registration of a new reservation in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "500", description = "Invalid data in the form",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PostMapping(value = "/{idhabitacion}")
    public ResponseEntity<?> addReserva(@PathVariable final Long idhabitacion, @RequestBody final ReservaDto reservaDto){
        if(!habitacionService.checkHabitacion(idhabitacion)){
            Reserva reserva = reservaService.addReserva(Reserva.from(reservaDto));
            Habitacion habitacion = habitacionService.changeEstadoHabitacion(idhabitacion);
            return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
        }
        return new ResponseEntity<>("La habitación actualmente está ocupada", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get the list of all reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getReservas(){
        List<Reserva> reservaList = reservaService.getReservas();
        List<ReservaDto> reservaDtoList = reservaList.stream().map(ReservaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(reservaDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Get one Reservation by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @GetMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> getReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.getReserva(idreserva);
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

    @Operation(summary = "Delete one Reservation by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @DeleteMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> deleteReserva(@PathVariable final Long idreserva){
        Reserva reserva = reservaService.deleteReserva(idreserva);
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

    @Operation(summary = "Update one Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Server error",
                    content = @Content)})

    @PutMapping(value = "/{idreserva}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable final Long idreserva, @RequestBody final ReservaDto reservaDto){
        Reserva reserva = reservaService.updateReserva(idreserva, Reserva.from(reservaDto));
        return new ResponseEntity<>(ReservaDto.from(reserva), HttpStatus.OK);
    }

}
