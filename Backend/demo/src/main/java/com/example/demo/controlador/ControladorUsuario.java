package com.example.demo.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.servicio.usuario.UsuarioServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tienda/api/usuario")
public class ControladorUsuario {

    private UsuarioServicio usuarioServicio;

    public ControladorUsuario(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid CrearUsuarioDTO crearUsuarioDTO) {
        return ResponseEntity.ok(usuarioServicio.crearUsuarioDTO(crearUsuarioDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(){
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }
    
}
