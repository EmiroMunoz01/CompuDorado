package com.example.demo.servicio.usuario;

import java.util.List;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTOAdmin;

public interface IUsuario {

    public List<UsuarioDTO> listarUsuarios();

    public List<UsuarioDTOAdmin> listarUsuariosAdmin();

    public UsuarioDTO listarUsuarioPorId(Integer idUsuario);

    public UsuarioDTO listarUsuarioPorCedula(Integer cedulaUsuario);

    public UsuarioDTOAdmin listarUsuarioPorCedulaAdmin(Integer cedulaUsuario);

    public UsuarioDTO editarUsuarioPorId(EditarUsuarioDTO editarUsuarioDTO, Integer idUsuario);

    public UsuarioDTO editarUsuarioPorCedula(EditarUsuarioDTO editarUsuarioDTO, Integer cedulaUsuario);

    public void eliminarUsuarioPorId(Integer idUsuario);

    public void eliminarUsuarioPorCedula(Integer cedulaUsuario);

    public UsuarioDTO crearUsuarioDTO(CrearUsuarioDTO crearUsuarioDTO);


}