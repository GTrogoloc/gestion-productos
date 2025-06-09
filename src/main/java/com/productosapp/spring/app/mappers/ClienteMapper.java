package com.productosapp.spring.app.mappers;

import com.productosapp.spring.app.models.Cliente;
import com.productosapp.spring.app.dtos.ClienteRequestDTO;
import com.productosapp.spring.app.dtos.ClienteResponseDTO;


public class ClienteMapper {
    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        return cliente;
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNombreCliente(),
            cliente.getApellido(),
            cliente.getTelefono(),
            cliente.getDireccion()
        );
    }


    
}
