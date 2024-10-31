package com.usta.proyecto_myla.models.DAOS;

import com.usta.proyecto_myla.entities.ProductoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductoDAO extends CrudRepository<ProductoEntity, Long> {
    @Transactional
    @Query("SELECT pro FROM ProductoEntity  pro WHERE pro.idProducto = ?1")
    public ProductoEntity viewDetail(Long id);
}
