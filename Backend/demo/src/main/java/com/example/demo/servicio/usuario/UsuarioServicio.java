package com.example.demo.servicio.usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTOAdmin;
import com.example.demo.modelo.usuario.ROLE;
import com.example.demo.modelo.usuario.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;

import jakarta.persistence.EntityNotFoundException;

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

    private UsuarioDTOAdmin convertirUsuarioEnDTOAdmin(Usuario usuario) {

        UsuarioDTOAdmin dtoResponse = new UsuarioDTOAdmin();

        dtoResponse.setId(usuario.getId());
        dtoResponse.setNombre(usuario.getNombre());
        dtoResponse.setApellido(usuario.getApellido());
        dtoResponse.setCedula(usuario.getCedula());
        dtoResponse.setEmail(usuario.getEmail());
        dtoResponse.setPassword(usuario.getPassword());
        dtoResponse.setDireccion(usuario.getDireccion());
        dtoResponse.setRole(usuario.getRole());

        return dtoResponse;
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

        Usuario buscarUsuario = repositorioUsuario.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + id));

        return convertirUsuarioEnDTO(buscarUsuario);
    }

    @Override
    public UsuarioDTO listarUsuarioPorCedula(Integer cedulaUsuario) {

        Usuario buscarUsuario = repositorioUsuario.findUsuarioByCedula(cedulaUsuario).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + cedulaUsuario));

        return convertirUsuarioEnDTO(buscarUsuario);
    }

    @Override
    public List<UsuarioDTOAdmin> listarUsuariosAdmin() {
        List<Usuario> usuarios = repositorioUsuario.findAll();
        List<UsuarioDTOAdmin> usuariosConvertidos = new ArrayList<>(usuarios.size());

        for (Usuario u : usuarios) {
            usuariosConvertidos.add(convertirUsuarioEnDTOAdmin(u));
        }

        return usuariosConvertidos;
    }

    @Override
    public UsuarioDTOAdmin listarUsuarioPorCedulaAdmin(Integer cedulaUsuario) {
        Usuario buscarUsuario = repositorioUsuario.findUsuarioByCedula(cedulaUsuario).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + cedulaUsuario));

        return convertirUsuarioEnDTOAdmin(buscarUsuario);
    }

    @Override
    public UsuarioDTO editarUsuarioPorId(EditarUsuarioDTO editarUsuarioDTO, Integer idUsuario) {

        Usuario existente = repositorioUsuario.findById(idUsuario).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + idUsuario));

        existente.setPassword(editarUsuarioDTO.getPassword());
        existente.setDireccion(editarUsuarioDTO.getDireccion());

        repositorioUsuario.save(existente);

        return convertirUsuarioEnDTO(existente);
    }

    @Override
    public UsuarioDTO editarUsuarioPorCedula(EditarUsuarioDTO editarUsuarioDTO, Integer cedulaUsuario) {

        Usuario existente = repositorioUsuario.findUsuarioByCedula(cedulaUsuario).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + cedulaUsuario));

        existente.setPassword(editarUsuarioDTO.getPassword());
        existente.setDireccion(editarUsuarioDTO.getDireccion());

        repositorioUsuario.save(existente);

        return convertirUsuarioEnDTO(existente);
    }

    @Override
    public void eliminarUsuarioPorId(Integer id) {

        Usuario existente = repositorioUsuario.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + id));

        repositorioUsuario.delete(existente);
    }

    @Override
    public void eliminarUsuarioPorCedula(Integer cedulaUsuario) {

        Usuario existente = repositorioUsuario.findUsuarioByCedula(cedulaUsuario).orElseThrow(
                () -> new EntityNotFoundException("No se encontró usuario con el id: %s" + cedulaUsuario));
        repositorioUsuario.delete(existente);
    }

}
