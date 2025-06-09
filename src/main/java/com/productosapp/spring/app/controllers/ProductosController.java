package com.productosapp.spring.app.controllers;

import com.productosapp.spring.app.dtos.ProductoRequestDTO;
import com.productosapp.spring.app.dtos.ProductoResponseDTO;
//import com.productosapp.spring.app.models.EnumMaterial;
import com.productosapp.spring.app.models.Producto;
import com.productosapp.spring.app.services.ProductoService;
import com.productosapp.spring.app.mappers.ProductoMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    private final ProductoService productoService;

    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //GET: LISTAMOS LOS PRODUCTOS
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Producto> productos = productoService.listarTodos();
    
        if (productos.isEmpty()) {
            return ResponseEntity.ok("No hay productos cargados.");
        }
    
        List<ProductoResponseDTO> productosDTO = productos.stream()
                .map(ProductoMapper::toDTO)
                .toList();
    
        return ResponseEntity.ok(productosDTO);
    }

    // GET: VER PRODUCTO POR ID y usando el dto
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
    Optional<Producto> productoOpt = productoService.buscarPorId(id);
    return productoOpt
        .map(producto -> ResponseEntity.ok(ProductoMapper.toDTO(producto)))  // Mapear Producto a DTO
        .orElseGet(() -> ResponseEntity.status(404).body(new ProductoResponseDTO(null, "Producto no encontrado", null, null, null, null, null)));
    }
   
   
 // BUSCAR PRODUCTO POR CODIGO
    @GetMapping("/buscar/codigo")
    public ResponseEntity<?> buscarPorCodigo(@RequestParam String codigo) {
    try {
        long codigoLong = Long.parseLong(codigo);
        Producto producto = productoService.buscarPorCodigo(codigoLong);
        ProductoResponseDTO dto = ProductoMapper.toDTO(producto);
        return ResponseEntity.ok(dto);
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body("El código debe ser un número válido.");
    } catch (ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getReason());
    }
    }

    //BUSCAR PRODUCTO POR NOMBRE
    @GetMapping("/buscar/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
    List<Producto> productos = productoService.buscarPorNombre(nombre);

    if (productos.isEmpty()) {
        return ResponseEntity.ok("No existe producto con ese nombre.");
    }
    
    List<ProductoResponseDTO> productosDTO = productos.stream()
            .map(ProductoMapper::toDTO)
            .toList();

    return ResponseEntity.ok(productosDTO);
    }

    // BUSCAR PRODUCTOS POR MATERIAL
    @GetMapping("/buscar/material")
    public ResponseEntity<?> buscarPorMaterial(@RequestParam String material) {
        try {
            List<Producto> productos = productoService.buscarPorMaterial(material);
            List<ProductoResponseDTO> productosDTO = productos.stream()
                    .map(ProductoMapper::toDTO)
                    .toList();
            return ResponseEntity.ok(productosDTO);
        } catch (ResponseStatusException e) {
            // Aquí capturamos la excepción y devolvemos el mensaje personalizado
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }


    // POST: CREAR EL PRODUCTO Y VALIDAR 
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ProductoRequestDTO productoRequestDTO) {

        Producto producto = ProductoMapper.toEntity(productoRequestDTO);
        String error = validarProducto(producto);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Producto guardado = productoService.guardar(producto);
        return ResponseEntity.status(201).body(ProductoMapper.toDTO(guardado));
    }

    // PUT: ACTUALIZAR EL PRODUCTO
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ProductoRequestDTO productoRequestDTO) {
       
        if (!productoService.existePorId(id)) {
            return ResponseEntity.status(404).body("Producto no encontrado.");
        }
        Producto producto = ProductoMapper.toEntity(productoRequestDTO);
        String error = validarProducto(producto);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        producto.setCodigo(id); // ACTUALIZAMOS SEGUN EL ID
        Producto actualizado = productoService.guardar(producto);
        return ResponseEntity.status(200).body(ProductoMapper.toDTO(actualizado));
    }

    // ELIMINAR PRODUCTO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!productoService.existePorId(id)) {
            return ResponseEntity.status(404).body("Producto no encontrado con ese código (Id).");
        }
        productoService.eliminarPorId(id);
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }

    // VALIDAMOS EL PRODUCTO 
    private String validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            return "El nombre es obligatorio.";
        }
        if (producto.getNombre().length() > 100) {
            return "El nombre no puede tener más de 100 caracteres.";
        }
        if (producto.getMedida() != null && producto.getMedida().length() > 50) {
            return "La medida no puede tener más de 50 caracteres.";
        }
        if (producto.getCantidad() != null && producto.getCantidad().length() > 50) {
            return "La cantidad no puede tener más de 50 caracteres.";
        }
        if (producto.getMaterial() == null) {
            return "El material es obligatorio.";
        }
        
        BigDecimal cero = new BigDecimal("0.00");
        if (producto.getPrecioUnitario() == null) {
        return "El precio unitario es obligatorio.";
        }
        if (producto.getPrecioUnitario().compareTo(cero) < 0) {
        return "El precio unitario debe ser mayor o igual a 0.";
        }

        if (producto.getPrecioTotal() == null) {
        return "El precio total es obligatorio.";
        }
        if (producto.getPrecioTotal().compareTo(cero) < 0) {
        return "El precio total debe ser mayor o igual a 0.";
        }

    return null; // Todo bien
    }
}