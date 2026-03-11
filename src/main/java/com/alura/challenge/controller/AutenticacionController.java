package com.alura.challenge.controller;

import com.alura.challenge.domain.usuario.DatosAutenticacionUsuario;
import com.alura.challenge.domain.usuario.Usuario;
import com.alura.challenge.security.DatosJWTToken;
import com.alura.challenge.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
            var usuarioAutenticado = manager.authenticate(authenticationToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fallo el login: " + e.getMessage());
        }
    }
}