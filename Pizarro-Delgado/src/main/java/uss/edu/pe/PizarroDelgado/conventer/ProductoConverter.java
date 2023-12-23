package uss.edu.pe.PizarroDelgado.conventer;
import org.springframework.stereotype.Component;

import uss.edu.pe.PizarroDelgado.dto.ProductoDTO;
import uss.edu.pe.PizarroDelgado.entity.Producto;



@Component
public class ProductoConverter extends AbstractConverter<Producto,ProductoDTO>{

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if(entity==null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.descripcion(entity.getDescripcion())
				.proveedor(entity.getProveedor())
				.stock(entity.getStock())
				.precio(entity.getPrecio())
				.estado(entity.getEstado())
				.categoria(entity.getCategoria())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if(dto==null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.descripcion(dto.getDescripcion())
				.proveedor(dto.getProveedor())
				.stock(dto.getStock())
				.precio(dto.getPrecio())
				.estado(dto.getEstado())
				.categoria(dto.getCategoria())
				.build();
	}
}
