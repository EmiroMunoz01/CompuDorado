package com.example.demo.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private String nombre;

    private Long cedula;

    private String apellido;

    private String email;

    private String direccion;

}
