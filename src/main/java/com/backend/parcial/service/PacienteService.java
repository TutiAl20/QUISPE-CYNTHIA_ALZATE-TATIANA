package com.backend.parcial.service;

import com.backend.parcial.dto.PacienteDto;
import com.backend.parcial.exception.ResourceNotFoundException;

import java.util.List;

public interface PacienteService {
    PacienteDto registrarPaciente(PacienteDto pacienteDto);
    List<PacienteDto> listarPacientes();
    PacienteDto buscarPacientePorId(Long id);
    PacienteDto buscarPacientePorDni(String dni);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
    PacienteDto actualizarPaciente(PacienteDto pacienteDto, Long id) throws ResourceNotFoundException;
}