package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.usuario.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByCedula(Integer cedula);

    void deleteUsuarioByCedula(Integer cedula);

}
