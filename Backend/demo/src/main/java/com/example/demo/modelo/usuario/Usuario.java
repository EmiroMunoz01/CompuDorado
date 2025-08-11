package com.example.demo.modelo.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private Integer cedula;

    private String email;
    private String password;

    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ROLE role;

}
