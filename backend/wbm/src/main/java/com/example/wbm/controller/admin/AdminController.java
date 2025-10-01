package com.example.wbm.controller.admin;

import com.example.wbm.implementation.*;
import com.example.wbm.model.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminController {

    private final RolServicioImpl rolServicio;
    private final PersonaServicioImpl personaServicio;
    private final UsuarioServicioImpl usuarioServicio;
    private final PerfilServicioImpl perfilServicio;
    private final PermisoServicioImpl permisoServicio;

    @GetMapping("")
    public String home (){
        return "/administrador/index";
    }

    @GetMapping("/usuarios")
    public String usuarios(
            Model model,
            @RequestParam(value = "idUsuarioEditar", required = false) Integer idUsuarioEditar,
            @RequestParam(value = "idUsuarioVer", required = false) Integer idUsuarioVer
    ) {
        // Modal para agregar usuario
        CDUsuarioDTO agregarUsuario = new CDUsuarioDTO();
        model.addAttribute("agregarUsuario", agregarUsuario);

        // Modal para editar usuario
        CDUsuarioDTO editarUsuario = null;
        if (idUsuarioEditar != null) {
            editarUsuario = usuarioServicio.leerUsuarioPorId(idUsuarioEditar);
        } else {
            editarUsuario = new CDUsuarioDTO();
        }
        model.addAttribute("editarUsuario", editarUsuario);

        // Modal para ver persona vinculada
        CDUsuarioDTO verUsuario = null;
        if (idUsuarioVer != null) {
            verUsuario = usuarioServicio.leerUsuarioPorId(idUsuarioVer);
        } else {
            verUsuario = new CDUsuarioDTO();
        }
        model.addAttribute("verUsuario", verUsuario);

        // Lista de usuarios
        List<CDUsuarioDTO> usuarios = usuarioServicio.leerUsuarios();
        model.addAttribute("usuarios", usuarios);

        // Lista de personas disponibles
        List<CDPersonaDTO> personasDisponibles = personaServicio.leerPersonasSinUsuario();
        model.addAttribute("personasDisponibles", personasDisponibles);

        return "/administrador/table";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("agregarUsuario") CDUsuarioDTO usuarioDTO) {
        usuarioDTO.setEstado(1);
        usuarioServicio.crear(usuarioDTO);
        return "redirect:/administrador/usuarios";
    }

    @PostMapping("/usuarios/editar")
    public String editarUsuario(@ModelAttribute("editarUsuario") CDUsuarioDTO usuarioDTO) {
        usuarioServicio.editarUsuario(usuarioDTO);
        return "redirect:/administrador/usuarios";
    }

    @PostMapping("/usuarios/cambiarEstado")
    public String cambiarEstadoUsuario(@RequestParam("idUsuario") Integer idUsuario) {
        usuarioServicio.cambiarEstadoUsuario(idUsuario);
        return "redirect:/administrador/usuarios";
    }



    @GetMapping("/roles")
    public String roless(Model model) {

        RolDTO rolDTO = RolDTO.builder()
                .estado(1) // inicializamos en 1
                .build();
        model.addAttribute("guardarRol", rolDTO);

        List<RolDTO> roles = rolServicio.leerRoles();
        model.addAttribute("roles", roles);

        RolDTO rolEditar =RolDTO.builder()
                .build();
        model.addAttribute("editarRol",rolEditar);

        return "/administrador/admin-roles";
    }


    @PostMapping("/roles/guardarRoles")
    public String guardarRoles(@Valid @ModelAttribute("guardarRol") RolDTO rolDTO){

        rolServicio.guardarRol(rolDTO);

        return "redirect:/administrador/roles";
    }

    @PostMapping("/roles/editarRoles")
    public String editarRoles(@Valid @ModelAttribute("editarRol") RolDTO rolDTO){

        rolServicio.editarRol(rolDTO);

        return "redirect:/administrador/roles";
    }

    @PostMapping("/roles/cambiarEstado")
    public String cambiarEstadoRol(@RequestParam("idRol") Integer idRol) {
        rolServicio.cambiarEStadoRol(idRol);
        return "redirect:/administrador/roles";
    }

    @GetMapping("/personas")
    public String personas(Model model) {
        // Inicializamos DTOs para los modales
        CDPersonaDTO personaGuardar = new CDPersonaDTO();
        model.addAttribute("guardarPersona", personaGuardar);

        CDPersonaDTO personaEditar = new CDPersonaDTO();
        model.addAttribute("editarPersona", personaEditar);

        // Traemos lista de personas
        List<CDPersonaDTO> personas = personaServicio.leerPersonas();
        model.addAttribute("personas", personas);

        return "/administrador/admin-personas";
    }

    @PostMapping("/personas/guardar")
    public String guardarPersona(@ModelAttribute("guardarPersona") CDPersonaDTO personaDTO) {
        personaServicio.crear(personaDTO);
        return "redirect:/administrador/personas";
    }

    @PostMapping("/personas/editar")
    public String editarPersona(@ModelAttribute("editarPersona") CDPersonaDTO personaDTO) {
        personaServicio.editarPersona(personaDTO);
        return "redirect:/administrador/personas";
    }

    @PostMapping("/personas/cambiarEstado")
    public String cambiarEstadoPersona(@RequestParam("idPersona") Integer idPersona) {
        personaServicio.cambiarEStadoPersona(idPersona);
        return "redirect:/administrador/personas";
    }

    @GetMapping("/perfiles")
    public String perfiles(Model model) {
        // 1. DTO para el modal "Agregar Perfil" (th:object="${agregarPerfil}")
        CDPerfilDTO agregarPerfil = new CDPerfilDTO();
        model.addAttribute("agregarPerfil", agregarPerfil);

        // 2. Lista de perfiles para la tabla (th:each="perfil : ${perfiles}")
        List<CDPerfilDTO> perfiles = perfilServicio.leerPerfiles();
        model.addAttribute("perfiles", perfiles);

        // 3. Listas para los <select> de Usuario y Rol
        // Nota: El HTML usa estas listas tanto en el modal de Agregar como en el de Editar (aunque este último está deshabilitado)
        List<CDUsuarioDTO> usuariosDisponibles = usuarioServicio.leerUsuarios();
        model.addAttribute("usuariosDisponibles", usuariosDisponibles);

        List<RolDTO> rolesDisponibles = rolServicio.leerRoles();
        model.addAttribute("rolesDisponibles", rolesDisponibles);

        List<PermisoDTO> permisosDisponibles = permisoServicio.leerPermisos(); // Asume este método y DTO existen
        model.addAttribute("permisosDisponibles", permisosDisponibles);

        // Ya no se necesitan los parámetros @RequestParam(value = "idPerfilEditar", ...)
        // porque el modal de edición/vista se llena con JS (data-* attributes).

        return "/administrador/admin-perfiles"; // Vista correcta
    }

    @PostMapping("/perfiles/guardar")
    public String guardarPerfil(@Valid @ModelAttribute("agregarPerfil") CDPerfilDTO perfilDTO) {
        perfilDTO.setEstado(1);
        perfilServicio.crearPerfil(perfilDTO);
        return "redirect:/administrador/perfiles";
    }

    @PostMapping("/perfiles/editar")
    public String editarPerfil(@ModelAttribute("editarPerfil") CDPerfilDTO perfilDTO) {
        // El formulario de edición del HTML solo envía: idPerfil, estado (hidden), idUsuario, idRol.
        // El Service debe ser capaz de manejar esta edición parcial.
        perfilServicio.editarPerfil(perfilDTO);
        return "redirect:/administrador/perfiles";
    }

    @PostMapping("/perfiles/cambiarEstado")
    public String cambiarEstadoPerfil(@RequestParam("idPerfil") Integer idPerfil) {
        perfilServicio.cambiarEstadoPerfil(idPerfil);
        return "redirect:/administrador/perfiles";
    }
}