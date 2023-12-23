package com.niquen.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.niquen.demo.converter.InventarioConverter;
import com.niquen.demo.dto.InventarioDTO;
import com.niquen.demo.entity.Inventario;
import com.niquen.demo.service.InventarioService;
import com.niquen.demo.utils.WrapperResponse;

@RestController
@RequestMapping("/v1/inventarios")
public class InventarioController {
	@Autowired
	private InventarioService service;
	
	@Autowired
	private InventarioConverter converter;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping()
	public ResponseEntity<List<InventarioDTO>> findAll(
			@RequestParam(value="producto",required=false,defaultValue = "")String producto,
			@RequestParam(value="offset",required=false,defaultValue = "0")int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue = "5")int pageSize
			){
		Pageable page= PageRequest.of(pageNumber,pageSize);
		List<Inventario> inventarios;
		if (producto==null) {
			inventarios=service.findAll(page);
			
		}else {
			inventarios=service.findByProducto(producto, page);
		}

		List<InventarioDTO> inventariosDTO=converter.fromEntity(inventarios);
		return new WrapperResponse(true,"success",inventariosDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<InventarioDTO>> findById(@PathVariable("id")int id){
		Inventario inventario=service.findById(id);
		InventarioDTO inventarioDTO=converter.fromEntity(inventario);
		return new WrapperResponse<InventarioDTO>(true,"success",inventarioDTO).createResponse(HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping()
	public ResponseEntity<InventarioDTO> create(@RequestBody InventarioDTO inventarioDTO){
		Inventario registro=service.save(converter.fromDTO(inventarioDTO));
		InventarioDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value="/{id}")
	public ResponseEntity<InventarioDTO> update(@PathVariable("id")int id,@RequestBody InventarioDTO inventarioDTO){
		Inventario registro=service.update(converter.fromDTO(inventarioDTO));
		InventarioDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value="/{id}")
	public ResponseEntity<InventarioDTO>delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
