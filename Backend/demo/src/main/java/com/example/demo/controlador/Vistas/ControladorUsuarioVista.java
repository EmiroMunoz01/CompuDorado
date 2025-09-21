package com.example.demo.controlador.Vistas;

import java.util.Optional;

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
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.modelo.usuario.Usuario;
import com.example.demo.servicio.usuario.UsuarioServicio;

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

    // En tu controlador web (no el de la API)
// Asegúrate de que estás usando el ID para buscar, es más robusto.
    @GetMapping("/editar/{cedulaUsuario}")
    public String mostrarFormularioEdicion(@PathVariable Long cedulaUsuario, Model model, RedirectAttributes flash) {

        // 1. Busca el usuario por su ID
        UsuarioDTO usuarioOptional = usuarioServicio.listarUsuarioPorCedula(cedulaUsuario);

        // 2. Valida si el usuario existe
        if (usuarioOptional.isEmpty()) {
            flash.addFlashAttribute("error", "Usuario no encontrado.");
            return "redirect:/tienda/usuario/listar";
        }

        // 3. Pasa el usuario y el título a la vista
        model.addAttribute("usuario", usuarioOptional.get());
        model.addAttribute("titulo", "Editar Usuario");

        // 4. Muestra la nueva vista de edición
        return "usuario/formulario-edicion"; // Nombre del archivo HTML
    }

}
