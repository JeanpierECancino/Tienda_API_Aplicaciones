package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sucursal;



@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    Page<Sucursal> findAll(org.springframework.data.domain.Pageable page);
    Sucursal findByNombre(String nombre);
}
