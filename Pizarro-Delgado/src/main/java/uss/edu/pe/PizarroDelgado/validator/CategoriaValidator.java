package uss.edu.pe.PizarroDelgado.validator;

import uss.edu.pe.PizarroDelgado.entity.Categoria;
import uss.edu.pe.PizarroDelgado.exceptions.ValidateServiceException;

public class CategoriaValidator {
	public static void save(Categoria categoria) {
		if(categoria.getNombre()==null || categoria.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(categoria.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}
}
