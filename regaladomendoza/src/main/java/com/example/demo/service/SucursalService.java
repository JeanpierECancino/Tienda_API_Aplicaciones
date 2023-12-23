package com.example.demo.service;

import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll(Pageable page);
    Sucursal findById(int id);
    Sucursal save(Sucursal sucursal);
    Sucursal update(Sucursal sucursal);
    void delete(int id);
}
