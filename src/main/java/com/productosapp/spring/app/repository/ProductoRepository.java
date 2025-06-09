package com.productosapp.spring.app.repository;

import com.productosapp.spring.app.models.EnumMaterial;
import com.productosapp.spring.app.models.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


//aca agregamos los metodos para buscar por codigo, nombre o material 
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCodigo(Long codigo);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByMaterial(EnumMaterial material); 
}
