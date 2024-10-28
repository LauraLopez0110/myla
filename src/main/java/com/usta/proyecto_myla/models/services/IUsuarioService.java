package com.usta.proyecto_myla.models.services;

import com.usta.proyecto_myla.entities.UsuarioEntity;
import java.util.List;

public interface IUsuarioService {

    public List<UsuarioEntity> findAll();

    public void save(UsuarioEntity usuario);

    public UsuarioEntity findById(Long id);

    public void deleteById(Long id);

    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario);

    public UsuarioEntity findByEmail(String email);

    public UsuarioEntity viewDetail(Long id);
}
