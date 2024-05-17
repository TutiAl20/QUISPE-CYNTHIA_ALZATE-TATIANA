package com.backend.parcial.service;

import com.backend.parcial.model.Odontologo;
import java.util.List;

public interface OdontologoDAO {
    void save(Odontologo odontologo);
    List<Odontologo> findAll();
}
