package com.example.demo.controlador.Vistas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.usuario.CrearUsuarioDTO;
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

}
