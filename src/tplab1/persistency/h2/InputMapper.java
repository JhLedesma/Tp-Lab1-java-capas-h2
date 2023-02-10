package tplab1.persistency.h2;

import tplab1.application.Input;
import tplab1.persistency.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class InputMapper implements Mapper<Input> {

    @Override
    public Input map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Double amount = resultSet.getDouble("amount");
        String description = resultSet.getString("description");
        LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
        int dptoId = resultSet.getInt("dptoId");
        return new Input(id, amount, description, date, dptoId);
    }
}
