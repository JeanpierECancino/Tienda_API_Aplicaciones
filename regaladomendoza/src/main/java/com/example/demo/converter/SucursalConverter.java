package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.dto.SucursalDTO;
import com.example.demo.entity.Sucursal;

@Component
public class SucursalConverter extends AbstractConverter<Sucursal, SucursalDTO> {

    @Override
    public SucursalDTO fromEntity(Sucursal entity) {
        if (entity == null) return null;
        return SucursalDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .activo(entity.getActivo())
                .build();
    }

    @Override
    public Sucursal fromDTO(SucursalDTO dto) {
        if (dto == null) return null;
        return Sucursal.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .activo(dto.isActivo())
                .build();
    }
}
