package com.niquen.demo.service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.niquen.demo.entity.Inventario;

public interface InventarioService {
	public List<Inventario> findAll(Pageable page);
	public List<Inventario> findByProducto(String producto, Pageable page);
	public Inventario findById(int id);
	public Inventario save(Inventario inventario);
	public Inventario update(Inventario inventario);
	public void delete(int id);
}
