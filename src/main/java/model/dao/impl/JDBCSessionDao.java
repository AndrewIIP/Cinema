package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.SessionDao;
import model.dao.exceptions.DAOException;
import model.entity.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class JDBCSessionDao extends AbstractDao implements SessionDao {
    Connection connection;

    public JDBCSessionDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Session> getAll() {
        return null;
    }

    @Override
    public Session update(Session entity) throws DAOException {
        return null;
    }

    @Override
    public Session getEntityById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public void create(Session entity) throws DAOException {

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
