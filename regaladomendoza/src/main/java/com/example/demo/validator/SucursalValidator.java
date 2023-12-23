package com.example.demo.validator;

import com.example.demo.entity.Sucursal;
import com.example.demo.exceptions.ValidateServiceException;

public class SucursalValidator {
    public static void save(Sucursal sucursal) {
        if (sucursal.getNombre() == null || sucursal.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre de la sucursal es requerido");
        }
        if (sucursal.getNombre().length() > 100) {
            throw new ValidateServiceException("El nombre de la sucursal es muy largo");
        }
        
        // Puedes agregar más validaciones según tus requisitos específicos para la entidad Sucursal.
    }
}
