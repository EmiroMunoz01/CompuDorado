package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.producto.Producto;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Integer> {

    Optional<Producto> findProductoByIdentificador(String identificador);

    void deleteProductoByIdentificador(String identificador);

}
