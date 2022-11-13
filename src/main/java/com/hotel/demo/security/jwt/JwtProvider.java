package com.hotel.demo.security.jwt;

import com.hotel.demo.security.models.UserPrincipal;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JwtProvider {
    //logger para mostrar los errores
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    //Clave para verificar el token
    private Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //tiempo base de expiración
    @Value("${jwt.expirationInMs}")
    private int jwExpirationInMs;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwExpirationInMs * 1000L);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }

    //función que permite obtener el nombre de usuario con el token
    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    //función que premite validar nuestro token con la firma secreta
    //se controla cualquier error que pueda existir con el token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("Token no soportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("Token expirado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token vacío");
        }catch (SignatureException e){
            LOGGER.error("Falla en la firma");
        }
        return false;
    }

}
