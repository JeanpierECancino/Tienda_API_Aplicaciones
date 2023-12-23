package com.niquen.demo.converter;
import org.springframework.stereotype.Component;

import com.niquen.demo.dto.InventarioDTO;
import com.niquen.demo.entity.Inventario;

@Component
public class InventarioConverter extends AbstractConverter<Inventario, InventarioDTO> {
	@Override
	public InventarioDTO fromEntity(Inventario entity) {
		if (entity==null) return null;
		return InventarioDTO.builder()
				.id(entity.getId())
				.producto(entity.getProducto())
				.cantidad(entity.getCantidad())
				.descripcion(entity.getDescripcion())
				.build();	
		}

	@Override
	public Inventario fromDTO(InventarioDTO dto) {
		if (dto==null) return null;
		return Inventario.builder()
				.id(dto.getId())
				.producto(dto.getProducto())
				.cantidad(dto.getCantidad())
				.descripcion(dto.getDescripcion())
				.build();
		}
}
