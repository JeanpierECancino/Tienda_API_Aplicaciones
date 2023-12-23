package com.example.tienda.validator;

import com.example.tienda.entity.Cliente;
import com.example.tienda.exception.ValidateServiceException;

public class ClienteValidator {
	public static void save(Cliente cliente) {
		if (cliente.getNombre()==null||cliente.getNombre().isEmpty()){
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(cliente.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy largo");
		}
		if (cliente.getApellido()==null||cliente.getApellido().isEmpty()){
			throw new ValidateServiceException("El apellido es requerido");
		}
		if(cliente.getApellido().length()>100) {
			throw new ValidateServiceException("El apellido es muy largo");
		}
		if (cliente.getDni()==null||cliente.getDni().isEmpty()){
			throw new ValidateServiceException("El DNI es requerido");
		}
		if(cliente.getDni().length()>8) {
			throw new ValidateServiceException("El DNI es muy largo");
		}
		if (cliente.getDireccion()==null||cliente.getDireccion().isEmpty()){
			throw new ValidateServiceException("La direccion es requerida");
		}
		if(cliente.getDireccion().length()>100) {
			throw new ValidateServiceException("La direccion es muy larga");
		}
		if (cliente.getTelefono()==null||cliente.getTelefono().isEmpty()){
			throw new ValidateServiceException("El telefono es requerido");
		}
		if(cliente.getTelefono().length()>9) {
			throw new ValidateServiceException("El telefono es muy largo");
		}
	}
}
