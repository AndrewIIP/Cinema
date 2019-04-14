package model.dao;

import model.dao.exceptions.DAOException;

import java.sql.*;
import java.util.List;
import java.util.Locale;

public interface GenericDao<E, K> extends AutoCloseable {

    List<E> getAll();
    E update(E entity) throws DAOException;
    E getEntityById(K id) throws DAOException;
    void delete(K id) throws DAOException;
    void create(E entity) throws DAOException;

    @Override
    void close();
}