# GESTION DE PRODUCTOS
Proyecto desarrollado en Spring Boot para la gestion de productos permitiendo Crear, Actualizar, Eliminar y Listar Productos. 
Forma parte de un sistema más amplio que incluirá entidades como Clientes y Pedidos.

## INTEGRANTES: 
- Gaston Trogolo

El código está organizado siguiendo buenas prácticas, separa las responsabilidades en los siguientes paquetes:
 - controllers : controladores REST que manejan las peticiones HTTP.
 - services : lógica de negocio.
 - repository : acceso a datos usando Spring Data JPA.
 - models : entidades del dominio como "Productos".
 - dtos : objetos para la transferencia de datos.
 - exceptions : manejo centralizado de errores.
 - mappers : conversión entre entidades y DTOs.

## Cómo ejecutar el proyecto
1. Tener instalado Java 17
2. Tener instalado Maven
3. Clonar el repositorio
4. Ejecutar con:
   ```bash
   ./mvnw spring-boot:run
