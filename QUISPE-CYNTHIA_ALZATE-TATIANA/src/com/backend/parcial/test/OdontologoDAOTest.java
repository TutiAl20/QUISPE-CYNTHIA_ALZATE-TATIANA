package com.backend.parcial.test;

import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoDAO;
import com.backend.parcial.service.OdontologoDAOImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OdontologoDAOTest {
    private OdontologoDAO odontologoDAO;

    @Before
    public void setUp() {
        odontologoDAO = new OdontologoDAOImpl();
        odontologoDAO.deleteAll();
    }

    @Test
    public void testSaveAndFindAll() {
        Odontologo odontologo1 = new Odontologo("1234", "Felipe", "Maillane");
        Odontologo odontologo2 = new Odontologo("5678", "Isabel", "Manrique");
        odontologoDAO.save(odontologo1);
        odontologoDAO.save(odontologo2);

        List<Odontologo> odontologos = odontologoDAO.findAll();
        assertEquals(2, odontologos.size());
        assertTrue(odontologos.contains(odontologo1));
        assertTrue(odontologos.contains(odontologo2));
    }
}