package uss.edu.pe.PizarroDelgado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uss.edu.pe.PizarroDelgado.conventer.ProductoConverter;
import uss.edu.pe.PizarroDelgado.dto.ProductoDTO;
import uss.edu.pe.PizarroDelgado.entity.Categoria;
import uss.edu.pe.PizarroDelgado.entity.Producto;
import uss.edu.pe.PizarroDelgado.service.CategoriaService;
import uss.edu.pe.PizarroDelgado.service.ProductoService;



@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@Autowired
	private CategoriaService serviceCat;
	@Autowired
	private ProductoConverter converterprod;
	@GetMapping()
	public ResponseEntity<List<ProductoDTO>> getAll(
			@RequestParam(value="nombre",required=false,defaultValue="") String nombre,
			@RequestParam(value="offset",required=false,defaultValue="0") int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue="5") int pageSize
			){
		Pageable page=PageRequest.of(pageNumber,pageSize);
		List<Producto> productos;
		if(nombre == null) {
			productos=service.findAll(page);
		}else {
			productos=service.findByNombre(nombre, page);
		}
		if(productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<ProductoDTO> productoDTO = converterprod.fromEntity(productos);
		return ResponseEntity.ok(productoDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductoDTO> getById(@PathVariable("id") int id) {
		Producto producto = service.findById(id);
		ProductoDTO productoDTO=converterprod.fromEntity(producto);
		return ResponseEntity.status(HttpStatus.OK).body(productoDTO);
	}
	
	@PostMapping
	public ResponseEntity<Producto> create(@RequestBody Producto producto) {
		Producto productoDb=service.create(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Producto> update(@RequestBody Producto producto) {
		Producto productoDb=service.update(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Producto> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
		
	}
	
	@GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        Pageable page = null;
		return serviceCat.findAll(page);
    }
	
}
