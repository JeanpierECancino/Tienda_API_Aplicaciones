package com.niquen.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niquen.demo.entity.Inventario;
import com.niquen.demo.exception.GeneralServiceException;
import com.niquen.demo.exception.NoDataFoundException;
import com.niquen.demo.exception.ValidateServiceException;
import com.niquen.demo.repository.InventarioRepository;
import com.niquen.demo.service.InventarioService;
import com.niquen.demo.validator.InventarioValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventarioServiceImpl  implements InventarioService{
	@Autowired
	private InventarioRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Inventario> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Inventario> findByProducto(String producto, Pageable page) {
		try {
			return repository.findByProductoContaining(producto,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Inventario findById(int id) {
		try {
			Inventario registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Inventario save(Inventario inventario) {
		try {
			InventarioValidator.save(inventario);
			inventario.setActivo(true);
			Inventario registro=repository.save(inventario);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Inventario update(Inventario inventario) {
		try {
			InventarioValidator.save(inventario);
			Inventario registro=repository.findById(inventario.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));

			registro.setProducto(inventario.getProducto());
			registro.setCantidad(inventario.getCantidad());
			registro.setDescripcion(inventario.getDescripcion());
			repository.save(registro);
			return registro;
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Inventario registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
