package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Sucursal;
import com.example.demo.exceptions.GeneralServiceException;
import com.example.demo.exceptions.NoDataFoundException;
import com.example.demo.exceptions.ValidateServiceException;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.service.SucursalService;
import com.example.demo.validator.SucursalValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Sucursal findById(int id) {
        try {
            Sucursal registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe el registro con ese ID"));
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Sucursal save(Sucursal sucursal) {
        try {
            SucursalValidator.save(sucursal);
            if (repository.findByNombre(sucursal.getNombre()) != null) {
                throw new ValidateServiceException("Ya existe un registro con el nombre" + sucursal.getNombre());
            }
            sucursal.setActivo(true);
            Sucursal registro = repository.save(sucursal);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Sucursal update(Sucursal sucursal) {
        try {
            SucursalValidator.save(sucursal);
            Sucursal registro = repository.findById(sucursal.getId()).orElseThrow(() -> new NoDataFoundException("No existe el registro con ese ID"));
            Sucursal registroD = repository.findByNombre(sucursal.getNombre());
            if (registroD != null && registroD.getId() != sucursal.getId()) {
                throw new ValidateServiceException("Ya existe un registro con el nombre" + sucursal.getNombre());
            }
            registro.setNombre(sucursal.getNombre());
            registro.setDireccion(sucursal.getDireccion());
            repository.save(registro);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
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
            Sucursal registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe el registro con ese ID"));
            repository.delete(registro);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
