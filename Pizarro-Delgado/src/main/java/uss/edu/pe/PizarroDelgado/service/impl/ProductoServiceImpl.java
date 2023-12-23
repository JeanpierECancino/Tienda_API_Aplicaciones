package uss.edu.pe.PizarroDelgado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;
import uss.edu.pe.PizarroDelgado.entity.Producto;
import uss.edu.pe.PizarroDelgado.exceptions.GeneralServiceException;
import uss.edu.pe.PizarroDelgado.exceptions.NoDataServiceException;
import uss.edu.pe.PizarroDelgado.exceptions.ValidateServiceException;
import uss.edu.pe.PizarroDelgado.repository.ProductoRepository;
import uss.edu.pe.PizarroDelgado.service.ProductoService;
import uss.edu.pe.PizarroDelgado.validator.ProductoValidator;


@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll(Pageable page) {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(int id) {
		try {
			return repository.findById(id).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID "+id));
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Producto create(Producto obj) {
		try {
			//Validación
			ProductoValidator.save(obj);
			if(repository.findByNombre(obj.getNombre()) !=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre"+obj.getNombre());
			}
			//Guardamos
			obj.setEstado("Activo");
			return repository.save(obj);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Producto update(Producto obj) {
		try {
			ProductoValidator.save(obj);
			Producto productoDB=repository.findByNombre(obj.getNombre());
			if(productoDB!=null&& obj.getId()!=productoDB.getId()) {
				throw new ValidateServiceException("No hay un registro con ese ID"+obj.getNombre());
			}
			Producto producto=repository.findById(obj.getId()).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID"+obj.getId()));
			//Actualizamos la categoría
			producto.setNombre(obj.getNombre());
			producto.setDescripcion(obj.getDescripcion());
			producto.setPrecio(obj.getPrecio());
			producto.setCategoria(obj.getCategoria());
			return repository.save(producto);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	@Override
	@Transactional
	public void delete(int id) {
		try {
			Producto producto=repository.findById(id).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID "+id));
			repository.delete(producto);
		}catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

}
