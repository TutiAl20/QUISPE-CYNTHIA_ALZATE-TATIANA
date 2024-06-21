package com.backend.parcial.service.impl;

import com.backend.parcial.dto.OdontologoDto;
import com.backend.parcial.dto.PacienteDto;
import com.backend.parcial.dto.TurnoDto;
import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.entity.Paciente;
import com.backend.parcial.entity.Turno;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.repository.OdontologoRepository;
import com.backend.parcial.repository.TurnoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TurnoServiceImplTest {

    @Mock
    private TurnoRepository turnoRepository;

    @Mock
    private OdontologoRepository odontologoRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private TurnoServiceImpl turnoService;

    @InjectMocks
    private OdontologoServiceImpl odontologoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuscarTurnoPoFecha() {
        // Arrange
        Long id = 1L;
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        paciente.setDni("123");
        paciente.setNombre("Jane");
        paciente.setApellido("Doe");
        Odontologo odontologo = new Odontologo();
        odontologo.setNumeroMatricula("12345");
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        TurnoDto turnoDto = new TurnoDto();
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setDni("123");
        pacienteDto.setNombre("Jane");
        pacienteDto.setApellido("Doe");
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNumeroMatricula("12345");
        odontologoDto.setNombre("John");
        odontologoDto.setApellido("Doe");
        turnoDto.setPaciente(pacienteDto);
        turnoDto.setOdontologo(odontologoDto);
        List<TurnoDto> turnos = new ArrayList<>();
        turnos.add(turnoDto);

        // Assert
        assertNotNull(turnoService.buscarPorOdontologo_Id(id));
    }

    @Test
    public void testRegistrarTurno() {
        // Arrange
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        paciente.setDni("123");
        paciente.setNombre("Jane");
        paciente.setApellido("Doe");
        Odontologo odontologo = new Odontologo();
        odontologo.setNumeroMatricula("12345");
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        TurnoDto turnoDto = new TurnoDto();
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setDni("123");
        pacienteDto.setNombre("Jane");
        pacienteDto.setApellido("Doe");
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNumeroMatricula("12345");
        odontologoDto.setNombre("John");
        odontologoDto.setApellido("Doe");
        turnoDto.setPaciente(pacienteDto);
        turnoDto.setOdontologo(odontologoDto);
        when(turnoRepository.save(any(Turno.class))).thenReturn(turno);

        // Act
        TurnoDto result = turnoService.registrarTurno(turnoDto);

        // Assert
        assertEquals(turnoDto, result);
    }

    @Test
    public void testEliminarTurno() throws ResourceNotFoundException {
        //Insert
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        paciente.setDni("123");
        paciente.setNombre("Jane");
        paciente.setApellido("Doe");
        Odontologo odontologo = new Odontologo();
        odontologo.setNumeroMatricula("12345");
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        TurnoDto turnoDto = new TurnoDto();
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setDni("123");
        pacienteDto.setNombre("Jane");
        pacienteDto.setApellido("Doe");
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNumeroMatricula("12345");
        odontologoDto.setNombre("John");
        odontologoDto.setApellido("Doe");
        turnoDto.setPaciente(pacienteDto);
        turnoDto.setOdontologo(odontologoDto);
        turnoService.registrarTurno(turnoDto);
        // Arrange
        Long id = 1L;
        when(turnoRepository.findById(id)).thenReturn(Optional.of(turno));

        // Act
        turnoService.eliminarTurno(id);
    }
}
