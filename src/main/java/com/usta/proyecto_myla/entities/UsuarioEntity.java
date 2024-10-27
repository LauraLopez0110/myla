package com.usta.proyecto_myla.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 50)
    @Column(name = "nombres_usuario", length = 50, nullable = false)
    private String nombresUsuario;

    @NotNull
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 1, max = 50)
    @Column(name = "apellidos_usuario", length = 50, nullable = false)
    private String apellidosUsuario;

    @NotNull
    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(min = 1, max = 50)
    @Column(name = "telefono_usuario", length = 50, nullable = false)
    private String telefonoUsuario;

    @NotNull
    @NotBlank(message = "La dirección no puede estar vacío")
    @Size(min = 1, max = 50)
    @Column(name = "direccion_usuario", length = 50, nullable = false)
    private String direccionUsuario;

    @NotNull
    @NotBlank(message = "El correo no puede estar vacío")
    @Size(min = 1, max = 50)
    @Column(name = "correo_usuario",unique = true, length = 50, nullable = false)
    private String correoUsuario;

    @NotNull
    @NotBlank(message = "La clave no puede estar vacío")
    @Size(min = 1, max = 100)
    @Column(name = "clave_usuario", length = 100, nullable = false)
    private String claveUsuario;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private Collection<RolEntity> role;
}
