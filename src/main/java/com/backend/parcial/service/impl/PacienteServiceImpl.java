package com.backend.parcial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.backend.parcial.dto.PacienteDto;
import com.backend.parcial.entity.Paciente;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.repository.PacienteRepository;
import com.backend.parcial.service.PacienteService;
import com.backend.parcial.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {
    //se mapea de DTO a entidad y viceversa
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteServiceImpl.class);
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PacienteDto registrarPaciente(PacienteDto pacienteDto) {
        //logica de negocio
        //mapeo de dto a entidad
        LOGGER.info("PacienteDto: " + JsonPrinter.toString(pacienteDto));
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
        LOGGER.info("PacienteEntidad: " + JsonPrinter.toString(paciente));
        if(buscarPacientePorDni(paciente.getDni()) == null) {
            Paciente pacienteRegistrado = pacienteRepository.save(paciente);
        }
        //mapeo de entidad a dto
        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarPacientes() {
        //mapeo de lista de entidades a lista de dtos
        List<PacienteDto> pacientes = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDto.class))
                .toList();
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientes));

        return pacientes;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {

        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteEncontrado = null;

        if (pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("No se ha encontrado el paciente con id {}", id);

        return pacienteEncontrado;
    }

    @Override
    public PacienteDto buscarPacientePorDni(String dni) {
        Paciente pacienteBuscado = pacienteRepository.findByDni(dni);
        PacienteDto pacienteEncontrado = null;

        if (pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("No se ha encontrado el paciente con dni {}", dni);
        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }

    @Override
    public PacienteDto actualizarPaciente(PacienteDto pacienteEntradaDto, Long id) throws ResourceNotFoundException {
        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteAActualizar = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteSalidaDto = null;

        if(pacienteAActualizar != null){

            pacienteRecibido.setId(pacienteAActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteAActualizar.getDomicilio().getId());
            pacienteAActualizar = pacienteRecibido;

            pacienteAActualizar.setNombre(pacienteRecibido.getNombre());
            pacienteAActualizar.setApellido(pacienteRecibido.getApellido());
            pacienteAActualizar.setDni(pacienteRecibido.getDni());
            pacienteAActualizar.setFechaIngreso(pacienteRecibido.getFechaIngreso());
            pacienteAActualizar.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
            pacienteAActualizar.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
            pacienteAActualizar.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());

            pacienteRepository.save(pacienteAActualizar);
            pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteDto.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
        }

        return pacienteSalidaDto;
    }

    private void configureMapping(){
        modelMapper.typeMap(PacienteDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteDto::setDomicilio));
    }
}