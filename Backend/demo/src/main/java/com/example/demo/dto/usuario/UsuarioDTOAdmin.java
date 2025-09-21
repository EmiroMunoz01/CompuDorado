package com.example.demo.dto.usuario;

import com.example.demo.modelo.usuario.ROLE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOAdmin {

    private Integer id;

    private String nombre;

    private String apellido;

    private Long cedula;

    private String email;

    private String password;

    private String direccion;

    private ROLE role;

}
