package com.backend.parcial.controller;

import com.backend.parcial.service.OdontologoService;
import com.backend.parcial.dto.OdontologoDto;
import com.backend.parcial.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
@CrossOrigin
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/listar")
    ResponseEntity<List<OdontologoDto>> listarOdontologos() {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/registrar")
    ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody @Valid OdontologoDto odontologoDto) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @GetMapping("/actualizar/{id}")
    ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody @Valid OdontologoDto odontologoDto, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoDto, id), HttpStatus.OK);
    }

    @GetMapping("/eliminar")
    ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
