package com.hotel.demo.controllers;

import com.hotel.demo.enumerados.ListaRoles;
import com.hotel.demo.models.dto.MessageDto;
import com.hotel.demo.models.dto.NewUserDto;
import com.hotel.demo.models.dto.UserLoginDto;
import com.hotel.demo.models.entities.Rol;
import com.hotel.demo.models.entities.Usuario;
import com.hotel.demo.security.jwt.JwtProvider;
import com.hotel.demo.security.jwt.JwtResponse;
import com.hotel.demo.services.RolService;
import com.hotel.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RolService rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody final UserLoginDto userLoginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> newUser (@Valid @RequestBody final NewUserDto newUserDTO){
        if(userService.existByNombreUsuario(newUserDTO.getUsername())){
            return new ResponseEntity<>(new MessageDto("El usuario ya existe."), HttpStatus.BAD_REQUEST);
        }
        Usuario user = new Usuario(newUserDTO.getUsername(), passwordEncoder.encode(newUserDTO.getPassword()));
        ArrayList<Rol> roles = new ArrayList<>();
        roles.add(rolService.getByNombreRol(ListaRoles.USER));
        if(newUserDTO.getRoles().contains("ADMIN") || newUserDTO.getRoles().contains("admin") ){
            roles.add(rolService.getByNombreRol(ListaRoles.ADMIN));
        }
        user.setRoles(roles);
        userService.saveUsuario(user);
        return new ResponseEntity<>(new MessageDto("Usuario creado."), HttpStatus.CREATED);
    }



}
