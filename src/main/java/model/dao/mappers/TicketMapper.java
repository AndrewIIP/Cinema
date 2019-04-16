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
        Ticket ticket = new Ticket();

        ticket.setId(resultSet.getInt(columnIndexes[0]));
        ticket.setPlace(resultSet.getInt(columnIndexes[1]));
        ticket.setUserID(resultSet.getInt(columnIndexes[2]));
        ticket.setSessionID(resultSet.getInt(columnIndexes[3]));

        return ticket.notEmpty() ? ticket : null;
    }

    @Override
    public Ticket makeUnique(Map<Integer, Ticket> cache, Ticket entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
