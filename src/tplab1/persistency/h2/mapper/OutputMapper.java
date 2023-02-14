package tplab1.persistency.h2.mapper;

import tplab1.application.model.Output;
import tplab1.persistency.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OutputMapper implements Mapper<Output> {

    @Override
    public Output map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Double amount = resultSet.getDouble("amount");
        String description = resultSet.getString("description");
        LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
        return new Output(id, amount, description, date);
    }
}
