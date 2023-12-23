package com.niquen.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventarioDTO {
	private int id;
	private String producto;
	private int cantidad;
	private String descripcion;
}
