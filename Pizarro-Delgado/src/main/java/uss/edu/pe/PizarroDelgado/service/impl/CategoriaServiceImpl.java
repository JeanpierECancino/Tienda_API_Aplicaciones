package uss.edu.pe.PizarroDelgado.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;
import uss.edu.pe.PizarroDelgado.entity.Categoria;
import uss.edu.pe.PizarroDelgado.exceptions.GeneralServiceException;
import uss.edu.pe.PizarroDelgado.exceptions.NoDataServiceException;
import uss.edu.pe.PizarroDelgado.exceptions.ValidateServiceException;
import uss.edu.pe.PizarroDelgado.repository.CategoriaRepository;
import uss.edu.pe.PizarroDelgado.service.CategoriaService;
import uss.edu.pe.PizarroDelgado.validator.CategoriaValidator;



@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll(Pageable page) {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(int id) {
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
	public List<Categoria> findByNombre(String nombre, Pageable page) {
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
	public Categoria create(Categoria obj) {
		try {
			//Validación
			CategoriaValidator.save(obj);
			if(repository.findByNombre(obj.getNombre()) !=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre"+obj.getNombre());
			}
			//Guardamos la categoría
			obj.setEstado(1);
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
	public Categoria update(Categoria obj) {
		try {
			CategoriaValidator.save(obj);
			Categoria categoriaDb=repository.findByNombre(obj.getNombre());
			if(categoriaDb!=null&& obj.getId()!=categoriaDb.getId()) {
				throw new ValidateServiceException("No hay un registro con ese Nombre"+obj.getNombre());
			}
			Categoria categoria=repository.findById(obj.getId()).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID"+obj.getId()));
			categoria.setNombre(obj.getNombre());
			return repository.save(categoria);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public void delete(int id) {
		try {
			Categoria categoria=repository.findById(id).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID "+id));
			repository.delete(categoria);
		}catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

}
