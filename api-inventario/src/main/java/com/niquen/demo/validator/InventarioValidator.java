package com.niquen.demo.validator;
import com.niquen.demo.entity.Inventario;
import com.niquen.demo.exception.ValidateServiceException;
public class InventarioValidator {
	public static void save(Inventario inventario) {
		if (inventario.getProducto()==null||inventario.getProducto().isEmpty()){
			throw new ValidateServiceException("El producto es requerido");
		}
		if(inventario.getProducto().length()>20) {
			throw new ValidateServiceException("El producto es muy largo");
		}
		if (inventario.getDescripcion()==null||inventario.getDescripcion().isEmpty()){
			throw new ValidateServiceException("La descripcion es requerida");
		}
		if(inventario.getDescripcion().length()>100) {
			throw new ValidateServiceException("La descripcion es muy larga");
		}
	}
}
