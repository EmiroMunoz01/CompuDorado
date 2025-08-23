package com.example.demo.servicio.usuario;

import java.util.List;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;

public interface IUsuario {

    
    public List<UsuarioDTO> listarUsuarios();

    public UsuarioDTO listarUsuarioPorId(Integer id);

    public UsuarioDTO editarUsuario(EditarUsuarioDTO editarUsuarioDTO, Integer idUsuario);

    public void eliminarUsuario(Integer id);

    public UsuarioDTO crearUsuarioDTO(CrearUsuarioDTO crearUsuarioDTO);


}