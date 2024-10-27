package com.usta.proyecto_myla.entities;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ROLES")
public class RolEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_rol", length = 50, nullable = false)
    private String nombreRol;

    public RolEntity(String nombreRol) {
        super();
        this.nombreRol = nombreRol;
    }

    public RolEntity() {

    }
}
