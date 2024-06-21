package com.backend.parcial.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.parcial.dto.OdontologoDto;
import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.repository.OdontologoRepository;
import com.backend.parcial.service.OdontologoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.MappingEngineImpl;

@RunWith(MockitoJUnitRunner.class)
public class OdontologoServiceImplTest {

    @Mock
    private OdontologoRepository odontologoRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private OdontologoServiceImpl odontologoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarOdontologos() {
        // Arrange
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(new Odontologo());
        when(odontologoRepository.findAll()).thenReturn(odontologos);

        // Act
        List<OdontologoDto> result = odontologoService.listarOdontologos();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testBuscarOdontologoPorNumeroMatricula() {
        // Arrange
        String numeroMatricula = "123";
        Odontologo odontologo = new Odontologo();
        odontologo.setNumeroMatricula(numeroMatricula);
        when(odontologoRepository.findByNumeroMatricula(numeroMatricula)).thenReturn(odontologo);

        // Act
        OdontologoDto result = odontologoService.buscarOdontologoPorNumeroMatricula(numeroMatricula);

        // Assert
        assertEquals(numeroMatricula, result.getNumeroMatricula());
    }

    @Test
    public void testRegistrarOdontologo() {
        // Arrange
        Odontologo odontologo = new Odontologo();
        odontologo.setNumeroMatricula("123");
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNumeroMatricula("123");
        odontologoDto.setNombre("John");
        odontologoDto.setApellido("Doe");
        when(odontologoRepository.save(any(Odontologo.class))).thenReturn(odontologo);

        // Act
        OdontologoDto result = odontologoService.registrarOdontologo(odontologoDto);

        // Assert
        assertEquals(odontologoDto, result);
    }

    @Test
    public void testEliminarOdontologo() throws ResourceNotFoundException {
        //Insert
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNumeroMatricula("123");
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNumeroMatricula("123");
        odontologoDto.setNombre("John");
        odontologoDto.setApellido("Doe");
        odontologoService.registrarOdontologo(odontologoDto);
        // Arrange
        Long id = 1L;
        when(odontologoRepository.findById(id)).thenReturn(Optional.of(odontologo));

        // Act
        odontologoService.eliminarOdontologo(id);
    }
}
