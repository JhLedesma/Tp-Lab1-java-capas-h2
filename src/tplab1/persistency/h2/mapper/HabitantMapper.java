package tplab1.persistency.h2.mapper;

import tplab1.application.model.Habitant;
import tplab1.persistency.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HabitantMapper implements Mapper<Habitant> {

    @Override
    public Habitant map(ResultSet resultSet) throws SQLException {
        int dni = resultSet.getInt("dni");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int dptoId = resultSet.getInt("dptoId");
        return new Habitant(dni, name, surname, dptoId);
    }
}
