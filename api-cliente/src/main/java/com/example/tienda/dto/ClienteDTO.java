package com.example.tienda.dto;

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
public class ClienteDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private String direccion;
	private String telefono;
}
