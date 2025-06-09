package com.productosapp.spring.app.dtos;

public class ClienteResponseDTO {
    
    private Long id;
    private String nombreCliente;
    private String apellido;
    private Long telefono;
    private String direccion;
    
// Constructor
public ClienteResponseDTO(Long id, String nombreCliente, String apellido, Long telefono, String direccion) {
    this.id = id;
    this.nombreCliente = nombreCliente;
    this.apellido = apellido;
    this.telefono = telefono;
    this.direccion = direccion;
}

// Getters y Setters
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNombreCliente() {
    return nombreCliente;
}

public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
}

public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public Long getTelefono() {
    return telefono;
}

public void setTelefono(Long telefono) {
    this.telefono = telefono;
}

public String getDireccion() {
    return direccion;
}

public void setDireccion(String direccion) {
    this.direccion = direccion;
}

}
