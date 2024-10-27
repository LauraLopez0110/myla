package com.usta.proyecto_myla.models.services;

import com.usta.proyecto_myla.entities.RolEntity;
import java.util.List;

public interface IRolService {

    public List<RolEntity> findAll();

    public void save(RolEntity rol);

    public RolEntity findById(Long id);

    public void deleteById(Long id);

    public RolEntity actualizarRolEntity(RolEntity rol);

}
