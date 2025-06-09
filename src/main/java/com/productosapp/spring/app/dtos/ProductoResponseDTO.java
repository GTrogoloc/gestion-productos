package com.productosapp.spring.app.dtos;
import java.math.BigDecimal;

import com.productosapp.spring.app.models.EnumMaterial;

//aca usamos record para no tener que hacer getters y setters
public record ProductoResponseDTO
(    Long codigo,
     String nombre,
     String medida,
     String cantidad,
     EnumMaterial material,
     BigDecimal precioUnitario,
     BigDecimal precioTotal)
{}
