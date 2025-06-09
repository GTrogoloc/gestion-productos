package com.productosapp.spring.app.services;

import com.productosapp.spring.app.dtos.PedidoResponseDTO;
import com.productosapp.spring.app.mappers.PedidoMapper;
import com.productosapp.spring.app.models.Pedido;
import com.productosapp.spring.app.repository.PedidoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;


    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Obtener todos los pedidos, convertidos a DTOs
    public List<PedidoResponseDTO> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener un pedido por su ID, convertido a DTO
    public PedidoResponseDTO obtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return PedidoMapper.toDTO(pedido);
    }

    // Crear un nuevo pedido
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Eliminar un pedido
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
 