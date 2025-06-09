package com.productosapp.spring.app.services;

import com.productosapp.spring.app.models.EnumMaterial;
import com.productosapp.spring.app.models.Producto;
import com.productosapp.spring.app.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        productoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return productoRepository.existsById(id);
    }

    public Producto buscarPorCodigo(Long codigo) {
    return productoRepository.findById(codigo)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con ese código."));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorMaterial(String material) {
        EnumMaterial enumMaterial = convertirAMaterial(material);
        List<Producto> productos = productoRepository.findByMaterial(enumMaterial);
    
        if (productos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron productos con el material: " + enumMaterial);
        }
    
        return productos;
    }

    //convertir a EnumMaterial  (probando)
    public EnumMaterial convertirAMaterial(String valor) {
        try {
            return EnumMaterial.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material inválido: '" + valor + "'. Los materiales válidos son: " + List.of(EnumMaterial.values()));
        }
    }


    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(productoActualizado.getNombre());
            p.setMedida(productoActualizado.getMedida());
            p.setCantidad(productoActualizado.getCantidad());
            p.setMaterial(productoActualizado.getMaterial());
            p.setPrecioUnitario(productoActualizado.getPrecioUnitario());
            p.setPrecioTotal(productoActualizado.getPrecioTotal());
            return productoRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

}