package com.niquen.demo.entity;

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
@Table(name="inventarios")
public class Inventario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true,nullable=false,length=20)
	private String producto;
	@Column(unique=false,nullable=false)
	private int cantidad;
	@Column(unique=true,nullable=false,length=100)
	private String descripcion;
	@Column(name="activo",nullable=false)
	private Boolean activo;
	
}
