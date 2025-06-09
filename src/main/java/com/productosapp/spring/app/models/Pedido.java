package com.productosapp.spring.app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.CascadeType;



@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @ManyToOne //muchos pedidos a un cliente
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EnumEstadoPedido estadoPedido;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> items;


//getters y setters
   public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EnumEstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EnumEstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }   

    
}
