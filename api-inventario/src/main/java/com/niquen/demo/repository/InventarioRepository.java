package com.niquen.demo.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.niquen.demo.entity.Inventario;

@Repository
public interface InventarioRepository  extends JpaRepository<Inventario, Integer>{
	List<Inventario> findByProductoContaining(String producto,Pageable page);
	Inventario findByProducto(String producto);
}
