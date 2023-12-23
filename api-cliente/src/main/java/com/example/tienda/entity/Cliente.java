package com.example.tienda.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true,nullable=false,length=100)
	private String nombre;
	@Column(unique=false,nullable=false,length=100)
	private String apellido;
	@Column(unique=true,nullable=false,length=8)
	private String dni;
	@Column(unique=false,nullable=false,length=100)
	private String direccion;
	@Column(unique=true,nullable=false,length=9)
	private String telefono;
	@Column(name="activo",nullable=false)
	private Boolean activo;
	

}
