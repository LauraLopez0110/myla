package com.usta.proyecto_myla.models.services;

import com.usta.proyecto_myla.entities.ProductoEntity;

import java.util.List;

public interface IProductoService {

    public List<ProductoEntity> findAll();

    public void save(ProductoEntity vehiculo);

    public ProductoEntity findById(Long id);

    public void deleteById(Long id);

    public ProductoEntity actualizarProductoEntity(ProductoEntity producto);

    public ProductoEntity viewDetail(Long id);
}
