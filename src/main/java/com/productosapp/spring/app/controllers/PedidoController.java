package com.productosapp.spring.app.controllers;

import com.productosapp.spring.app.models.Pedido;
import com.productosapp.spring.app.services.PedidoService;
import com.productosapp.spring.app.dtos.PedidoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //OBTENER TODOS LOS PEDIDOS
    @GetMapping
    public List<PedidoResponseDTO> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    //OBTENER PEDIDO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            PedidoResponseDTO pedido = pedidoService.obtenerPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //CREAR PEDIDO
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    //ELIMINAR PEDIDO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
    
}
