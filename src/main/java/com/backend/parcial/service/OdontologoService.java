package com.backend.parcial.service;

import com.backend.parcial.dto.OdontologoDto;
import com.backend.parcial.exception.ResourceNotFoundException;

import java.util.List;

public interface OdontologoService {

    OdontologoDto registrarOdontologo(OdontologoDto odontologoDto);
    List<OdontologoDto> listarOdontologos();
    OdontologoDto buscarOdontologoPorId(Long id);
    OdontologoDto buscarOdontologoPorNumeroMatricula(String numeroMatricula);
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    OdontologoDto actualizarOdontologo(OdontologoDto odontologoDto, Long id) throws ResourceNotFoundException;
}
