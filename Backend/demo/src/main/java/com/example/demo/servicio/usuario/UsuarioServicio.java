package com.example.demo.servicio.usuario;

import java.util.List;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;

public class UsuarioServicio implements IUsuario{



    
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarUsuarios'");
    }

    @Override
    public UsuarioDTO listarUsuarioPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarUsuarioPorId'");
    }

    @Override
    public EditarUsuarioDTO editarUsuario(EditarUsuarioDTO editarUsuarioDTO, Integer idUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarUsuario'");
    }

    @Override
    public void eliminarUsuario(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
    }

    @Override
    public UsuarioDTO crearUsuarioDTO(CrearUsuarioDTO crearUsuarioDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearUsuarioDTO'");
    }


}
