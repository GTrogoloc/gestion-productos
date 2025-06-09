package com.productosapp.spring.app.mappers;

import com.productosapp.spring.app.dtos.ItemPedidoResponseDTO;
import com.productosapp.spring.app.dtos.PedidoResponseDTO;
import com.productosapp.spring.app.models.Pedido;
import java.util.List;

public class PedidoMapper {
    public static PedidoResponseDTO toDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setClienteNombre(pedido.getCliente().getNombreCliente());
        dto.setEstadoPedido(pedido.getEstadoPedido());

        List<ItemPedidoResponseDTO> items = pedido.getItems().stream().map(item -> {
            ItemPedidoResponseDTO itemDTO = new ItemPedidoResponseDTO();
            itemDTO.setProductoNombre(item.getProducto().getNombre());
            itemDTO.setCantidad(item.getCantidad());
            itemDTO.setPrecioUnitario(item.getProducto().getPrecioUnitario().doubleValue());
            itemDTO.setSubTotal(item.getCantidad() * item.getProducto().getPrecioUnitario().doubleValue());
            return itemDTO;
        }).toList();

        dto.setItems(items);

        return dto;
    
}
}
