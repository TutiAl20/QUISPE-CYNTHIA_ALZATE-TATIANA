package com.backend.parcial.repository;

import com.backend.parcial.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    // Métodos de consulta específicos para Turno
    List<Turno> findByFechaYHora(LocalDateTime fechaYHora);
    List<Turno> findByOdontologo_Id(Long odontologoId);
    List<Turno> findByPaciente_Id(Long pacienteId);
}
