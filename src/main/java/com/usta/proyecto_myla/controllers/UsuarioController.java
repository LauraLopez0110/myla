package com.usta.proyecto_myla.controllers;

import com.usta.proyecto_myla.entities.RolEntity;
import com.usta.proyecto_myla.entities.UsuarioEntity;
import com.usta.proyecto_myla.models.services.IRolService;
import com.usta.proyecto_myla.models.services.IUsuarioService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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

    }
