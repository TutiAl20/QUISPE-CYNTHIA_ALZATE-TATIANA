package com.backend.parcial.controller;

import com.backend.parcial.service.TurnoService;
import com.backend.parcial.dto.TurnoDto;
import com.backend.parcial.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("turnos")
@CrossOrigin
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/registrar")
    ResponseEntity<TurnoDto> registrarTurno(@RequestBody @Valid TurnoDto turnoDto) {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoDto), HttpStatus.CREATED);
    }

    @GetMapping("/buscarporfecha")
    ResponseEntity<List<TurnoDto>> buscarTurnoPorFecha(@RequestParam LocalDateTime fecha) {
        return new ResponseEntity<>(turnoService.buscarPorFechaYHora(fecha), HttpStatus.OK);
    }

    @GetMapping("/buscarporpaciente")
    ResponseEntity<List<TurnoDto>> buscarTurnoPorPaciente(@RequestParam Long pacienteId) {
        return new ResponseEntity<>(turnoService.buscarPorPaciente_Id(pacienteId), HttpStatus.OK);
    }

    @GetMapping("/buscarporodontologo")
    ResponseEntity<List<TurnoDto>> buscarTurnoPorOdontologo(@RequestParam Long odontologoId) {
        return new ResponseEntity<>(turnoService.buscarPorOdontologo_Id(odontologoId), HttpStatus.OK);
    }

    @GetMapping("/actualizar/{id}")
    ResponseEntity<TurnoDto> actualizarTurno(@RequestBody @Valid TurnoDto turnoDto, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.actualizarTurno(turnoDto, id), HttpStatus.OK);
    }

    @GetMapping("/eliminar")
    ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
