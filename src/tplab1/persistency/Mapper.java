package tplab1.persistency;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    public T map(ResultSet resultSet) throws SQLException;
}
