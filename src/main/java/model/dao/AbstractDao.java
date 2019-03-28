package model.dao;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<E, K, V> {
    private Connection connection;

    public AbstractDao() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract List<E> getAll();
    public abstract E update(E entity);
    public abstract E getEntityById(K id);
    public abstract E getEntityByName(V name);
    public abstract E getEntityByEmail(V email);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    // Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
