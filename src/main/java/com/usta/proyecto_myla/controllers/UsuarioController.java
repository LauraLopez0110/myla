package com.usta.proyecto_myla.controllers;

import com.usta.proyecto_myla.entities.RolEntity;
import com.usta.proyecto_myla.entities.UsuarioEntity;
import com.usta.proyecto_myla.models.services.IRolService;
import com.usta.proyecto_myla.models.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private IRolService iRolService;

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/register")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioEntity()); // Asegúrate de que el nombre coincida con lo que usas en la plantilla
        return "register"; // Nombre de la vista Thymeleaf
    }
    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/register")
    public String register(@Valid UsuarioEntity usuario,BindingResult result,
                           @RequestParam(value = "rol") String rol,
                            SessionStatus status) {

        if (result.hasErrors()) {
            // Puedes imprimir los errores aquí para ver cuál es el problema
            System.out.println(result.getAllErrors());
            return "register";
        }



            String pass = new BCryptPasswordEncoder().encode(usuario.getClaveUsuario());
            usuario.setClaveUsuario(pass);
            usuario.setRole(Arrays.asList(new RolEntity(rol)));
            iUsuarioService.save(usuario);
            status.setComplete();

            return "redirect:/login";
        }

        /* ----------------------------------------------------------------------------- */

    @GetMapping(value = "/perfil")
    public String perfil(Model model) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Obtener el nombre de usuario

        // Obtener los detalles del usuario basado en el nombre de usuario
        UsuarioEntity usuario = iUsuarioService.findByEmail(username);

        // Agregar el usuario al modelo
        model.addAttribute("title", "Perfil");
        model.addAttribute("usuario", usuario);

        return "usuario/perfil"; // Asegúrate de que este sea el nombre de tu vista
    }

    /* ----------------------------------------------------------------------------- */
    }
