package com.backend.parcial.repository;

import com.backend.parcial.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    // Métodos de consulta específicos para Odontologo
    Odontologo findByNumeroMatricula(String numeroMatricula);
    Odontologo findByNombreAndApellido(String nombre, String apellido);
    Odontologo findById(String id);
}
