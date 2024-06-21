package com.backend.parcial.service.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.TurnoRepository;
import com.backend.parcial.service.TurnoService;
import com.backend.parcial.dto.TurnoDto;
import com.backend.parcial.entity.Paciente;
import com.backend.parcial.entity.Turno;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("turnoService")
public class TurnoServiceImpl implements TurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoServiceImpl.class);

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TurnoDto registrarTurno(TurnoDto turnoDto) {
        LOGGER.info("TurnoDto: " + JsonPrinter.toString(turnoDto));
        Turno turno = modelMapper.map(turnoDto, Turno.class);
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turno));
        Turno turnoRegistrado = turnoRepository.save(turno);
        //mapeo de entidad a dto
        return turnoDto;
    }

    @Override
    public List<TurnoDto> buscarPorFechaYHora(LocalDateTime fechaYHora) {
        //mapeo de lista de entidades a lista de dtos
        List<TurnoDto> turnos = turnoRepository.findByFechaYHora(fechaYHora)
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public List<TurnoDto> buscarPorOdontologo_Id(Long odontologoId) {
        //mapeo de lista de entidades a lista de dtos
        List<TurnoDto> turnos = turnoRepository.findByOdontologo_Id(odontologoId)
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public List<TurnoDto> buscarPorPaciente_Id(Long pacienteId) {
        //mapeo de lista de entidades a lista de dtos
        List<TurnoDto> turnos = turnoRepository.findByPaciente_Id(pacienteId)
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        if (turnoBuscado != null) {
            turnoRepository.delete(turnoBuscado);
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);
    }

    @Override
    public TurnoDto actualizarTurno(TurnoDto turnoDto, Long id) throws ResourceNotFoundException {
        Turno turnoRecibido = modelMapper.map(turnoDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoSalidaDto = null;
        if (turnoAActualizar != null) {
            turnoRecibido.setFechaYHora(turnoDto.getFechaYHora());
            turnoRecibido.setOdontologo(modelMapper.map(turnoDto.getOdontologo(), Odontologo.class));
            turnoRecibido.setPaciente(modelMapper.map(turnoDto.getPaciente(), Paciente.class));
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoDto.class);
            LOGGER.info("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
        }
        return turnoSalidaDto;
    }
}
