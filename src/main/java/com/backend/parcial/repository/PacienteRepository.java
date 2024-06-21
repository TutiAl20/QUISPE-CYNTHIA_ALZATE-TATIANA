package com.backend.parcial.repository;

import com.backend.parcial.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByDniAndNombre(String dni, String nombre);
    //@Query("SELECT p FROM Paciente p WHERE p.dni = ?1") HQL
    //@Query(value = "SELECT * FROM PACIENTES WHERE DNI = ?1", nativeQuery = true) SQL
    Paciente findByDni(String dni);
    Paciente findById(String id);
}