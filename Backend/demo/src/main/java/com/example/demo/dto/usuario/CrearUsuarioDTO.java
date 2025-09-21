package com.example.demo.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotNull(message = "La cedula es obligatoria")
    private Long cedula;

    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @NotBlank(message = "El password no puede estar vacío")
    private String password;

    @NotBlank(message = "La direccion no puede estar vacía")
    private String direccion;

}
