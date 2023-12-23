package uss.edu.pe.PizarroDelgado.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable=false, length =100, unique=true)
	private String nombre;
	@Column(name="descripcion", nullable=false, length =200)
	private String descripcion;
	@Column(name="proveedor", nullable=false, length =255)
	private String proveedor;
	@Column(name="stock", nullable=false)
	private int stock;
	@Column(name="precio" , nullable=false)
	private double precio;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	@Column(name="estado",nullable=false)
	private String estado;
	@Embedded
	private TimeStamp timeStamp;
}
