package com.backend.parcial.service.impl;

import com.backend.parcial.dto.PacienteDto;
import com.backend.parcial.entity.Paciente;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.repository.PacienteRepository;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PacienteServiceImplTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarPacientes() {
        // Arrange
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente());
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        // Act
        List<PacienteDto> result = pacienteService.listarPacientes();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testBuscarPacientePorDni() {
        // Arrange
        String dni = "123";
        Paciente paciente = new Paciente();
        paciente.setDni(dni);
        when(pacienteRepository.findByDni(dni)).thenReturn(paciente);

        // Act
        PacienteDto result = pacienteService.buscarPacientePorDni(dni);

        // Assert
        assertEquals(dni, result.getDni());
    }

    @Test
    public void testRegistrarPaciente() {
        // Arrange
        Paciente paciente = new Paciente();
        paciente.setDni("123");
        paciente.setNombre("John");
        paciente.setApellido("Doe");
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setDni("123");
        pacienteDto.setNombre("John");
        pacienteDto.setApellido("Doe");
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        // Act
        PacienteDto result = pacienteService.registrarPaciente(pacienteDto);

        // Assert
        assertEquals(pacienteDto, result);
    }

    @Test
    public void testEliminarPaciente() throws ResourceNotFoundException {
        //Insert
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setDni("123");
        paciente.setNombre("John");
        paciente.setApellido("Doe");
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setDni("123");
        pacienteDto.setNombre("John");
        pacienteDto.setApellido("Doe");
        pacienteService.registrarPaciente(pacienteDto);
        // Arrange
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        // Act
        pacienteService.eliminarPaciente(id);
    }
}
