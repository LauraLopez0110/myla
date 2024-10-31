package com.usta.proyecto_myla.entities;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PRODUCTOS")
public class ProductoEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iid_producto")
    private Long idProducto;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_producto" , length = 50, nullable = false)
    private String nombreProducto;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_producto" , length = 50, nullable = false)
    private String tipoProducto;

    @NotNull
    @Column(name = "precio_producto", nullable = false)
    private Float precioProducto;


    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion_producto" , length = 100, nullable = false)
    private String descripcionProducto;
}
