package com.productosapp.spring.app.dtos;

import com.productosapp.spring.app.models.EnumEstadoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private String clienteNombre; 
    private EnumEstadoPedido estadoPedido;
    private List<ItemPedidoResponseDTO> items;  // Lista de items asociados al pedido

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public EnumEstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EnumEstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<ItemPedidoResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoResponseDTO> items) {
        this.items = items;
    }
}
