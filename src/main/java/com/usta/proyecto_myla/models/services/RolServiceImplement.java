package com.usta.proyecto_myla.models.services;

import com.usta.proyecto_myla.entities.RolEntity;
import com.usta.proyecto_myla.models.DAOS.RolDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private RolDAO rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<RolEntity> findAll() {
        return (List<RolEntity>) rolDao.findAll();
    }

    @Override
    @Transactional
    public void save(RolEntity rol) {
        rolDao.save(rol);
    }

    @Override
    @Transactional
    public RolEntity findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolDao.deleteById(id);
    }

    @Override
    @Transactional
    public RolEntity actualizarRolEntity(RolEntity rol) {
        return rolDao.save(rol);
    }

}
