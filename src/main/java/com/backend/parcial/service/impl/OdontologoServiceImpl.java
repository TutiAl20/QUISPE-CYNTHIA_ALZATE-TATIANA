package com.backend.parcial.service.impl;

import com.backend.parcial.dto.OdontologoDto;
import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.exception.ResourceNotFoundException;
import com.backend.parcial.repository.OdontologoRepository;
import com.backend.parcial.service.OdontologoService;
import com.backend.parcial.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("odontologoService")
public class OdontologoServiceImpl implements OdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceImpl.class);
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OdontologoDto registrarOdontologo(OdontologoDto odontologoDto)  {
        LOGGER.info("OdontologoDto: " + JsonPrinter.toString(odontologoDto));
        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        LOGGER.info("OdontologoEntidad: " + JsonPrinter.toString(odontologo));
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        //mapeo de entidad a dto
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        //mapeo de lista de entidades a lista de dtos
        List<OdontologoDto> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDto.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologos));

        return odontologos;
    }

    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoEncontrado = null;

        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        return odontologoEncontrado;
    }

    @Override
    public OdontologoDto buscarOdontologoPorNumeroMatricula(String dni) {
        Odontologo odontologoBuscado = odontologoRepository.findByNumeroMatricula(dni);
        OdontologoDto odontologoEncontrado = null;

        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con dni {}", dni);
        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }
    }

    @Override
    public OdontologoDto actualizarOdontologo(OdontologoDto odontologoDto, Long id) throws ResourceNotFoundException {
        Odontologo odontologoRecibido = modelMapper.map(odontologoDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoSalidaDto = null;

        if(odontologoAActualizar != null){

            odontologoRecibido.setId(odontologoAActualizar.getId());
            odontologoRecibido.setNombre(odontologoAActualizar.getNombre());
            odontologoRecibido.setApellido(odontologoAActualizar.getApellido());
            odontologoRecibido.setNumeroMatricula(odontologoAActualizar.getNumeroMatricula());
            odontologoAActualizar = odontologoRecibido;         

            odontologoRepository.save(odontologoAActualizar);
            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
        }

        return odontologoSalidaDto;
    }
}
