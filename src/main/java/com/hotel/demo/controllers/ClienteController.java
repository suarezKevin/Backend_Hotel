package com.hotel.demo.controllers;

import com.hotel.demo.models.dto.ClienteDto;
import com.hotel.demo.models.entities.Cliente;
import com.hotel.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clientes")

//http://localhost:9095/hotel/clientes

public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> addCliente(@RequestBody final ClienteDto clienteDto){
        Cliente cliente = clienteService.addCliente(Cliente.from(clienteDto));
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getClientes(){
        List<Cliente> clientes = clienteService.getClientes();
        List<ClienteDto> clientesDto = clientes.stream().map(ClienteDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(clientesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable final Long idcliente){
        Cliente cliente = clienteService.getCliente(idcliente);
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable final Long idcliente){
        Cliente cliente = clienteService.deleteCliente(idcliente);
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }

    @PutMapping(value = "/{idcliente}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable final Long idcliente, @RequestBody final ClienteDto clienteDto){
        Cliente cliente = clienteService.updateCliente(idcliente, Cliente.from(clienteDto));
        return new ResponseEntity<>(ClienteDto.from(cliente), HttpStatus.OK);
    }


}
