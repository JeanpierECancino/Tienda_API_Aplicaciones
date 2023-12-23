package uss.edu.pe.PizarroDelgado.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uss.edu.pe.PizarroDelgado.entity.Categoria;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
	private int id;
	private String nombre;
	private String descripcion;
	private String proveedor;
	private int stock;
	private double precio;
	private String estado;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
}
