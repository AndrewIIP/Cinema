package model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException;

    T makeUnique(Map<Integer, T> cache, T entity);
}
