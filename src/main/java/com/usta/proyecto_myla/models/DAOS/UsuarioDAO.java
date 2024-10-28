package com.usta.proyecto_myla.models.DAOS;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.usta.proyecto_myla.entities.UsuarioEntity;


public interface UsuarioDAO extends CrudRepository<UsuarioEntity, Long> {

    // Método de búsqueda por correo electrónico
    @Transactional
    @Query("SELECT US FROM UsuarioEntity US WHERE US.correoUsuario = ?1")
    public UsuarioEntity findByEmail(String email);

    //Método de busqueda por id
    @Transactional
    @Query("SELECT US FROM UsuarioEntity US WHERE US.idUsuario = ?1")
    public UsuarioEntity viewDetail(Long id);

}