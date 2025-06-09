package com.productosapp.spring.app.repository;

import com.productosapp.spring.app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {    
}
