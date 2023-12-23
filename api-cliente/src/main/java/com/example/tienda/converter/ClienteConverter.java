package com.example.tienda.converter;

import org.springframework.stereotype.Component;

import com.example.tienda.dto.ClienteDTO;
import com.example.tienda.entity.Cliente;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDTO>{

	@Override
	public ClienteDTO fromEntity(Cliente entity) {
		if (entity==null) return null;
		return ClienteDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellido(entity.getApellido())
				.dni(entity.getDni())
				.direccion(entity.getDireccion())
				.telefono(entity.getTelefono())
				.build();	
		}

	@Override
	public Cliente fromDTO(ClienteDTO dto) {
		if (dto==null) return null;
		return Cliente.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellido(dto.getApellido())
				.dni(dto.getDni())
				.direccion(dto.getDireccion())
				.telefono(dto.getTelefono())
				.build();
		}
	}

