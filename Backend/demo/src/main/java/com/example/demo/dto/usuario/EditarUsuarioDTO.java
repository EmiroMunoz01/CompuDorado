package com.example.demo.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarUsuarioDTO {

    

    @NotBlank(message = "El password no puede estar vacío")
    private String password;

    @NotBlank(message = "La direccion no puede estar vacía")
    private String direccion;
    
}
