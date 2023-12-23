package uss.edu.pe.PizarroDelgado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import uss.edu.pe.PizarroDelgado.conventer.CategoriaConverter;
import uss.edu.pe.PizarroDelgado.dto.CategoriaDTO;
import uss.edu.pe.PizarroDelgado.entity.Categoria;
import uss.edu.pe.PizarroDelgado.service.CategoriaService;





@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	@Autowired
	private CategoriaConverter converterCat;
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> getAll(
			@RequestParam(value="nombre",required=false,defaultValue="") String nombre,
			@RequestParam(value="offset",required=false,defaultValue="0") int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue="5") int pageSize
			){
		Pageable page=PageRequest.of(pageNumber,pageSize);
		List<Categoria> categorias;
		if(nombre == null) {
			categorias=service.findAll(page);
		}else {
			categorias=service.findByNombre(nombre, page);
		}
		if(categorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<CategoriaDTO> categoriaDTO = converterCat.fromEntity(categorias);
		return ResponseEntity.ok(categoriaDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO> getById(@PathVariable("id") int id) {
		Categoria categoria = service.findById(id);
		CategoriaDTO categoriaDTO=converterCat.fromEntity(categoria);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.create(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.update(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
}
