package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.TicketDao;
import model.dao.exceptions.DAOException;
import model.entity.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class JDBCTicketDao extends AbstractDao implements TicketDao {
    Connection connection;

    public JDBCTicketDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Ticket update(Ticket entity) throws DAOException {
        return null;
    }

    @Override
    public Ticket getEntityById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public void create(Ticket entity) throws DAOException {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();//TODO LOG
        }
    }
}
