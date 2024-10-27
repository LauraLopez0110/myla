package com.usta.proyecto_myla.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.usta.proyecto_myla.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class securityConfiguration {
    
    @Autowired
    private JpaUserDetailsService userDetailsService;

    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize   // Permisos para Estudiantes
                        .requestMatchers("/crearEstudiante", "/editarEstudiante", "/eliminarEstudiante")
                        .hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/estudiantes", "/detalleEstudiante")
                        .hasAnyRole("ESTUDIANTE", "DOCENTE", "ADMINISTRADOR")

                        // Permisos para Docentes
                        .requestMatchers("/crearDocente", "/editarDocente", "/eliminarDocente")
                        .hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/docentes", "/detalleDocente")
                        .hasAnyRole("DOCENTE", "ADMINISTRADOR")

                        // Permisos para Tutorias
                        .requestMatchers("/crearTutoria", "/editarTutoria", "/eliminarTutoria")
                        .hasAnyRole("DOCENTE", "ADMINISTRADOR")
                        .requestMatchers("/tutorias", "/detalleTutoria")
                        .hasAnyRole("ESTUDIANTE", "DOCENTE", "ADMINISTRADOR")
                        .anyRequest()
                        .permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .successHandler(new LoginSuccessHandler())
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
                .exceptionHandling(exeptions -> exeptions
                        .accessDeniedPage("/error-404"));



        return http.build();

    }
}
