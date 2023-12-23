package com.example.tienda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tienda.entity.Cliente;
import com.example.tienda.exception.GeneralServiceException;
import com.example.tienda.exception.NoDataFoundException;
import com.example.tienda.exception.ValidateServiceException;
import com.example.tienda.repository.ClienteRepository;
import com.example.tienda.service.ClienteService;
import com.example.tienda.validator.ClienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService{
	@Autowired
	private ClienteRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll(Pageable page) {
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
	public List<Cliente> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
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
	public Cliente findById(int id) {
		try {
			Cliente registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
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
	public Cliente save(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			if (repository.findByNombre(cliente.getNombre())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre "+cliente.getNombre());
			}
			cliente.setActivo(true);
			Cliente registro=repository.save(cliente);
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
	public Cliente update(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			Cliente registro=repository.findById(cliente.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			Cliente registroD = repository.findByNombre(cliente.getNombre());
			if (registroD!=null && registroD.getId()!=cliente.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre "+cliente.getNombre());
			}
			registro.setNombre(cliente.getNombre());
			registro.setApellido(cliente.getApellido());
			registro.setDni(cliente.getDni());
			registro.setDireccion(cliente.getDireccion());
			registro.setTelefono(cliente.getTelefono());
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
			Cliente registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
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
