package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.converter.SucursalConverter;
import com.example.demo.dto.SucursalDTO;
import com.example.demo.entity.Sucursal;
import com.example.demo.service.SucursalService;
import com.example.demo.utils.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @Autowired
    private SucursalConverter converter;

    @GetMapping()
    public ResponseEntity<WrapperResponse<List<SucursalDTO>>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageOffset,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageOffset, pageSize);
        List<Sucursal> sucursales = service.findAll(page);
        List<SucursalDTO> sucursalesDTO = converter.fromEntity(sucursales);
        return new WrapperResponse<>(true, "success", sucursalesDTO).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<SucursalDTO>> findById(@PathVariable("id") int id) {
        Sucursal sucursal = service.findById(id);
        SucursalDTO sucursalDTO = converter.fromEntity(sucursal);
        return new WrapperResponse<>(true, "success", sucursalDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<WrapperResponse<SucursalDTO>> create(@RequestBody SucursalDTO sucursalDTO) {
        Sucursal registro = service.save(converter.fromDTO(sucursalDTO));
        SucursalDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse<>(true, "success", registroDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<SucursalDTO>> update(@PathVariable("id") int id, @RequestBody SucursalDTO sucursalDTO) {
        Sucursal registro = service.update(converter.fromDTO(sucursalDTO));
        SucursalDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse<>(true, "success", registroDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<Object>> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);
    }
}
