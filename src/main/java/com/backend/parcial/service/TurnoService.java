package com.backend.parcial.service;

import com.backend.parcial.dto.TurnoDto;
import com.backend.parcial.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoService {
    // Métodos de consulta específicos para Turno
    TurnoDto registrarTurno(TurnoDto turnoDto);
    List<TurnoDto> buscarPorFechaYHora(LocalDateTime fechaYHora);
    List<TurnoDto> buscarPorOdontologo_Id(Long odontologoId);
    List<TurnoDto> buscarPorPaciente_Id(Long pacienteId);
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoDto actualizarTurno(TurnoDto turnoDto, Long id) throws ResourceNotFoundException;
}
