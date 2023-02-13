package tplab1.persistency.h2;

import tplab1.application.model.Dpto;
import tplab1.persistency.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DptoMapper implements Mapper<Dpto> {

    @Override
    public Dpto map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String floor = resultSet.getString("floor");
        int size = resultSet.getInt("size");
        return new Dpto(id, floor, size);
    }
}