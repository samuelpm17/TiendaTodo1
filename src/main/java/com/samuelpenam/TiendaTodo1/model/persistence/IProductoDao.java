package com.samuelpenam.TiendaTodo1.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samuelpenam.TiendaTodo1.model.entity.Producto;

@Repository
public interface IProductoDao extends JpaRepository<Producto,Long>{
		
}
