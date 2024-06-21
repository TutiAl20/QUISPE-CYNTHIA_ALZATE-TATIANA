package com.backend.parcial.controller;

import com.backend.parcial.dto.PacienteDto;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@CrossOrigin
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    ResponseEntity<List<PacienteDto>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/registrar")
    ResponseEntity<PacienteDto> registrarPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @GetMapping("/actualizar/{id}")
    ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody @Valid PacienteDto pacienteDto, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteDto, id), HttpStatus.OK);
    }

    @GetMapping("/eliminar")
    ResponseEntity<?> eliminarPaciente(@RequestParam Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
