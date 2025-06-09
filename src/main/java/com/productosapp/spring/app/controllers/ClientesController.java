package com.productosapp.spring.app.controllers;

import com.productosapp.spring.app.dtos.ClienteRequestDTO;
import com.productosapp.spring.app.dtos.ClienteResponseDTO;
import com.productosapp.spring.app.models.Cliente;
import com.productosapp.spring.app.services.ClienteService;
import com.productosapp.spring.app.mappers.ClienteMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClienteService clienteService;

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    //LISTAR TODOS LOS CLIENTES
    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listarTodos()
            .stream()
            .map(ClienteMapper::toDTO)
            .toList();
    }

    //OBTENER CLIENTE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteService.buscarPorId(id);
        return clienteOpt
            .map(cliente -> ResponseEntity.ok(ClienteMapper.toDTO(cliente))) // Si se encuentra el cliente
            .orElseGet(() -> ResponseEntity.status(404).body(new ClienteResponseDTO(null, "Cliente no encontrado", "", null,"")));
    }





    //CREAR CLIENTE
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteService.guardar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDTO(guardado));
    }

    //ACTUALIZAR CLIENTE
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        if (!clienteService.existePorId(id)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }

        Cliente cliente = ClienteMapper.toEntity(dto);
        cliente.setId(id); // actualizar por ID

        Cliente actualizado = clienteService.guardar(cliente);
        return ResponseEntity.ok(ClienteMapper.toDTO(actualizado));
    }

    //ELIMINAR CLIENTE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!clienteService.existePorId(id)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }

        clienteService.eliminarPorId(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
    
    
}
