package uss.edu.pe.PizarroDelgado.conventer;

import org.springframework.stereotype.Component;

import uss.edu.pe.PizarroDelgado.dto.CategoriaDTO;
import uss.edu.pe.PizarroDelgado.entity.Categoria;





@Component
public class CategoriaConverter extends AbstractConverter<Categoria,CategoriaDTO>{
	@Override
	public CategoriaDTO fromEntity(Categoria entity) {
		if(entity==null) return null;
		return CategoriaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.estado(entity.getEstado())
				.build();
	}

	@Override
	public Categoria fromDTO(CategoriaDTO dto) {
		if(dto==null) return null;
		return Categoria.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.estado(dto.getEstado())
				.build();
	}
}
