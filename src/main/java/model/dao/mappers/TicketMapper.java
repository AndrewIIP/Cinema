package model.dao.mappers;

import model.dao.exceptions.DAOException;
import model.entity.Ticket;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements ObjectMapper<Ticket> {
    @Override
    public Ticket extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        return null;
    }

    @Override
    public Ticket makeUnique(Map<Integer, Ticket> cache, Ticket entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
