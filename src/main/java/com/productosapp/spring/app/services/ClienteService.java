package com.productosapp.spring.app.services;

import com.productosapp.spring.app.models.Cliente;
import com.productosapp.spring.app.repository.ClienteRepository;
//import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public boolean existePorId(Long id) {
        return clienteRepository.existsById(id);
    }

    public void eliminarPorId(Long id) {
        clienteRepository.deleteById(id);
    }

    
}
