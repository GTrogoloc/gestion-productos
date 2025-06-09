package com.productosapp.spring.app.dtos;

import java.math.BigDecimal;

import com.productosapp.spring.app.models.EnumMaterial;

public record ProductoRequestDTO(
// Long codigo es autogenerado por eso lo saco de aca!    
    String nombre,
    String medida,
    String cantidad,
    EnumMaterial material,
    BigDecimal precioUnitario,
    BigDecimal precioTotal
) {}
