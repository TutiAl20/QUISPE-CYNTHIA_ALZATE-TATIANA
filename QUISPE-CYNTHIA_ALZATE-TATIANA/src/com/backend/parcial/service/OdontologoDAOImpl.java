package com.backend.parcial.service;

import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOImpl implements OdontologoDAO {
    private static final Logger logger = Logger.getLogger(OdontologoDAOImpl.class);
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("RUNSCRIPT FROM 'src/main/resources/schema.sql'")) {
            statement.execute();
            logger.info("Database initialized.");
        } catch (SQLException e) {
            logger.error("Error initializing database", e);
        }
    }

    @Override
    public void save(Odontologo odontologo) {
        String sql = "INSERT INTO odontologo (numero_matricula, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, odontologo.getNumeroMatricula());
            statement.setString(2, odontologo.getNombre());
            statement.setString(3, odontologo.getApellido());
            statement.executeUpdate();
            logger.info("Odontologo saved: " + odontologo);
        } catch (SQLException e) {
            logger.error("Error saving odontologo", e);
        }
    }

    @Override
    public List<Odontologo> findAll() {
        List<Odontologo> odontologos = new ArrayList<>();
        String sql = "SELECT * FROM odontologo";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getString("numero_matricula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido")
                );
                odontologo.setId(resultSet.getInt("id"));
                odontologos.add(odontologo);
            }
            logger.info("Odontologos retrieved: " + odontologos);
        } catch (SQLException e) {
            logger.error("Error retrieving odontologos", e);
        }
        return odontologos;

    }
}


