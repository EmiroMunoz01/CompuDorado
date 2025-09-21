package com.example.demo.controlador.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.servicio.usuario.UsuarioServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tienda/api/usuario")
public class ControladorUsuario {

    private final UsuarioServicio usuarioServicio;

    public ControladorUsuario(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid CrearUsuarioDTO crearUsuarioDTO) {
        return ResponseEntity.ok(usuarioServicio.crearUsuarioDTO(crearUsuarioDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }

    @GetMapping("admin/listar")
    public ResponseEntity<?> listarUsuariosAdmin() {
        return ResponseEntity.ok(usuarioServicio.listarUsuariosAdmin());
    }

    @GetMapping("/listar/cedula/{cedulaUsuario}")
    public ResponseEntity<?> listarUsuarioPorCedula(@PathVariable Long cedulaUsuario) {
        return ResponseEntity.ok(usuarioServicio.listarUsuarioPorCedula(cedulaUsuario));
    }

    @GetMapping("admin/listar/cedula/{cedulaUsuario}")
    public ResponseEntity<?> listarUsuarioPorCedulaAdmin(@PathVariable Long cedulaUsuario) {
        return ResponseEntity.ok(usuarioServicio.listarUsuarioPorCedulaAdmin(cedulaUsuario));
    }

    @PutMapping("/editar/id/{idUsuario}")
    public ResponseEntity<?> editarUsuarioPorId(@RequestBody @Valid EditarUsuarioDTO usuario,
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(usuarioServicio.editarUsuarioPorId(usuario, idUsuario));
    }

    @PutMapping("/editar/cedula/{cedulaUsuario}")
    public ResponseEntity<?> editarUsuarioPorCedula(@RequestBody @Valid EditarUsuarioDTO usuario,
            @PathVariable Long cedulaUsuario) {
        return ResponseEntity.ok(usuarioServicio.editarUsuarioPorCedula(usuario, cedulaUsuario));
    }

    @DeleteMapping("/eliminar/cedula/{cedulaUsuario}")
    public ResponseEntity<?> eliminarUsuarioPorIdentificacion(@PathVariable Long cedulaUsuario) {
        usuarioServicio.eliminarUsuarioPorCedula(cedulaUsuario);
        return ResponseEntity.noContent().build(); // 204 sin body
    }

}
