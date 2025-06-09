package com.productosapp.spring.app.mappers;

//import com.productosapp.spring.app.models.EnumMaterial;
import com.productosapp.spring.app.models.Producto;
import com.productosapp.spring.app.dtos.ProductoRequestDTO;
import com.productosapp.spring.app.dtos.ProductoResponseDTO;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        //producto.setCodigo(dto.codigo());
        producto.setNombre(dto.nombre());
        producto.setMedida(dto.medida());
        producto.setCantidad(dto.cantidad());
        producto.setMaterial(dto.material());
        producto.setPrecioUnitario(dto.precioUnitario());
        producto.setPrecioTotal(dto.precioTotal());
        return producto;
    }

    public static ProductoResponseDTO toDTO(Producto producto) {
        return new ProductoResponseDTO(
            producto.getCodigo(), 
            producto.getNombre(),
            producto.getMedida(),
            producto.getCantidad(),
            producto.getMaterial(),
            producto.getPrecioUnitario(),
            producto.getPrecioTotal()
        );
    }}