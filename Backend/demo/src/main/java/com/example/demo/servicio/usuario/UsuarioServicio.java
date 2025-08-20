package com.example.demo.servicio.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.modelo.usuario.ROLE;
import com.example.demo.modelo.usuario.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;

@Service
public class UsuarioServicio implements IUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public UsuarioServicio(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;

    }

    private UsuarioDTO convertirUsuarioEnDTO(Usuario usuario) {

        UsuarioDTO dtoResponse = new UsuarioDTO();

        dtoResponse.setNombre(usuario.getNombre());
        dtoResponse.setApellido(usuario.getApellido());
        dtoResponse.setEmail(usuario.getEmail());
        dtoResponse.setDireccion(usuario.getDireccion());

        return dtoResponse;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        List<Usuario> usuarios = repositorioUsuario.findAll();
        List<UsuarioDTO> usuariosConvertidos = new ArrayList<>(usuarios.size());

        for (Usuario u : usuarios) {
            usuariosConvertidos.add(convertirUsuarioEnDTO(u));
        }

        return usuariosConvertidos;
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

        Optional<Usuario> buscarUsuario = repositorioUsuario.findUsuarioByCedula(crearUsuarioDTO.getCedula());

        if (buscarUsuario.isPresent()) {
            throw new IllegalArgumentException(
                    "El usuario con la cedula: ---" + crearUsuarioDTO.getCedula()
                            + "--- ya existe en la base de datos");
        }

        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setNombre(crearUsuarioDTO.getNombre());
        nuevoUsuario.setApellido(crearUsuarioDTO.getApellido());
        nuevoUsuario.setCedula(crearUsuarioDTO.getCedula());
        nuevoUsuario.setEmail(crearUsuarioDTO.getEmail());
        nuevoUsuario.setPassword(crearUsuarioDTO.getPassword());
        nuevoUsuario.setDireccion(crearUsuarioDTO.getDireccion());

        nuevoUsuario.setRole(ROLE.USER);

        repositorioUsuario.save(nuevoUsuario);

        return convertirUsuarioEnDTO(nuevoUsuario);

    }

}
