package com.example.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tienda.converter.ClienteConverter;
import com.example.tienda.dto.ClienteDTO;
import com.example.tienda.entity.Cliente;
import com.example.tienda.service.ClienteService;
import com.example.tienda.utils.WrapperResponse;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ClienteConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll(
			@RequestParam(value="nombre",required=false,defaultValue = "")String nombre,
			@RequestParam(value="offset",required=false,defaultValue = "0")int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue = "5")int pageSize
			){
		Pageable page= PageRequest.of(pageNumber,pageSize);
		List<Cliente> clientes;
		if (nombre==null) {
			clientes=service.findAll(page);
			
		}else {
			clientes=service.findByNombre(nombre, page);
		}

		List<ClienteDTO> clientesDTO=converter.fromEntity(clientes);
		return new WrapperResponse(true,"success",clientesDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<ClienteDTO>> findById(@PathVariable("id")int id){
		Cliente cliente=service.findById(id);
		ClienteDTO clienteDTO=converter.fromEntity(cliente);
		return new WrapperResponse<ClienteDTO>(true,"success",clienteDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO){
		Cliente registro=service.save(converter.fromDTO(clienteDTO));
		ClienteDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable("id")int id,@RequestBody ClienteDTO clienteDTO){
		Cliente registro=service.update(converter.fromDTO(clienteDTO));
		ClienteDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClienteDTO>delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
