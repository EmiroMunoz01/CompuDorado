package com.example.demo.controlador.Vistas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
import com.example.demo.dto.usuario.EditarUsuarioDTO;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.servicio.usuario.UsuarioServicio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tienda/usuario")
public class ControladorUsuarioVista {

    private final UsuarioServicio usuarioServicio;

    public ControladorUsuarioVista(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // 1. Mostrar el formulario (GET)
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new CrearUsuarioDTO());
        model.addAttribute("titulo", "Crear Usuario");
        return "usuario/crear";
    }

    @PostMapping("/crear")
    public String crearUsuario(@Valid @ModelAttribute("usuario") CrearUsuarioDTO crearUsuarioDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Usuario");
            return "usuario/crear";      // carpeta usuario
        }

        usuarioServicio.crearUsuarioDTO(crearUsuarioDTO);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
        return "redirect:/tienda/usuario/listar";
    }

    // Listar usuarios (vista web)
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        try {
            model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
            model.addAttribute("titulo", "Lista de Usuarios");
            return "usuario/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar usuarios: " + e.getMessage());
            return "usuario/listar";
        }
    }

    @PostMapping("/eliminar/{cedula}")
    public String eliminarUsuario(@PathVariable Long cedula, RedirectAttributes flash) {
        try {
            usuarioServicio.eliminarUsuarioPorCedula(cedula); // Llama al servicio para borrar el usuario
            flash.addFlashAttribute("success", "Usuario eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }

        // Redirigimos al usuario a la lista de productos
        return "redirect:/tienda/usuario/listar";
    }

    @GetMapping("/editar/{cedulaUsuario}")
    public String mostrarFormularioEdicion(@PathVariable Long cedulaUsuario, Model model, RedirectAttributes flash) {
        try {
            // Buscar el usuario para verificar que existe
            UsuarioDTO usuarioExistente = usuarioServicio.listarUsuarioPorCedula(cedulaUsuario);

            // Crear un EditarUsuarioDTO vacío para el formulario
            EditarUsuarioDTO editarUsuarioDTO = new EditarUsuarioDTO();
            // Pre-cargar con los datos actuales si quieres mostrar los valores existentes
            editarUsuarioDTO.setPassword(usuarioExistente.getPassword()); 
            editarUsuarioDTO.setDireccion(usuarioExistente.getDireccion());

            model.addAttribute("usuario", editarUsuarioDTO);
            model.addAttribute("cedula", cedulaUsuario); // Para pasarla al formulario

            return "usuario/editar";

        } catch (EntityNotFoundException e) {
            flash.addFlashAttribute("error", "El usuario no existe");
            return "redirect:/tienda/usuario/listar";
        }
    }

    @PostMapping("/editar/{cedulaUsuario}")
    public String procesarEdicion(@PathVariable Long cedulaUsuario,
            @ModelAttribute EditarUsuarioDTO editarUsuarioDTO,
            RedirectAttributes flash) {
        try {
            // Usar tu método existente para editar por cédula
            usuarioServicio.editarUsuarioPorCedula(editarUsuarioDTO, cedulaUsuario);

            flash.addFlashAttribute("success", "Usuario actualizado exitosamente");
            return "redirect:/tienda/usuario/listar";

        } catch (EntityNotFoundException e) {
            flash.addFlashAttribute("error", "El usuario no existe");
            return "redirect:/tienda/usuario/listar";
        }
    }

}
