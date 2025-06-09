package com.productosapp.spring.app.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleEnumParseError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException invalidFormatException
                && invalidFormatException.getTargetType().isEnum()) {

            Class<?> enumClass = invalidFormatException.getTargetType();

            List<String> valoresPermitidos = Arrays.stream(enumClass.getEnumConstants())
                    .map(Object::toString)
                    .toList();

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Material inválido. Valores permitidos: " + valoresPermitidos);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Otro error de parseo
        Map<String, Object> generic = new LinkedHashMap<>();
        generic.put("status", HttpStatus.BAD_REQUEST.value());
        generic.put("message", "Error al leer el cuerpo de la solicitud.");
        return new ResponseEntity<>(generic, HttpStatus.BAD_REQUEST);
    }
}
