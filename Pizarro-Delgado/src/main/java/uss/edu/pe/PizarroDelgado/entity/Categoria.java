package uss.edu.pe.PizarroDelgado.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Getter
@Setter
@Builder
@Entity
@Table(name="categorias")
@EntityListeners(AuditingEntityListener.class)
public class Categoria {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(unique = true,nullable = false, length = 100)
	private String nombre;	
	@Column(name="estado",nullable=false, columnDefinition="bit default 1")
	private int estado;
	@Embedded
	private TimeStamp timeStamp;
}
