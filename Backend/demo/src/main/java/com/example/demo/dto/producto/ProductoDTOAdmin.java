package com.example.demo.dto.producto;

import java.math.BigDecimal;

import com.example.demo.modelo.producto.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTOAdmin {

    private Integer id;

    private String identificador;

    private String nombre;

    private String descripcion;

    private Integer cantidad;

    private BigDecimal precio;

    private Categoria categoria;

}
