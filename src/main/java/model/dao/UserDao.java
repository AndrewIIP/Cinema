package model.dao;

import model.dao.exceptions.AlreadyExistsInDBException;
import model.dao.exceptions.DAOException;
import model.entity.User;
import model.spec.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User, Integer> {
    public List<User> getAll() {
        List<User> list = new ArrayList<>(20);
        String sqlQuery = "SELECT * FROM cinema.users;";
        PreparedStatement prepStatement = getPrepareStatement(sqlQuery);
        try {
            ResultSet resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setRole(Role.valueOf(resultSet.getString(5)));
                list.add(user);
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }

        return list;
    }

    public User update(User entity) throws DAOException {
        String sqlQuery = "UPDATE `cinema`.`users` t " +
                "SET t.`id` = ?, " +
                "t.`username` = ?, " +
                "t.`email` = ?, " +
                "t.`password` = ?, " +
                "t.`role` = ? " +
                "WHERE t.`id` = ?";

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
                prepStatement.setInt(1, entity.getId());
                prepStatement.setString(2, entity.getUsername());
                prepStatement.setString(3, entity.getEmail());
                prepStatement.setString(4, entity.getPassword());
                prepStatement.setString(5, entity.getRole().getString());
                prepStatement.setInt(6, entity.getId());
                try {
                    prepStatement.execute();
                } catch (SQLException e) {
                    //TODO LOG
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                //TODO LOG
                e.printStackTrace();
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return entity;
    }

    public User getEntityById(Integer id) throws DAOException {
        String sqlQuery = "SELECT * FROM cinema.users WHERE id = ?;";

        User user = new User();
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
                prepStatement.setInt(1, id);
                user = extractResultSet(prepStatement);
            } catch (SQLException e) {
                //TODO LOG
                e.printStackTrace();
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return user;
    }

    public User getEntityByUsername(String name) throws DAOException {
        String sqlQuery = "SELECT * FROM cinema.users WHERE username = ?;";
        return getUserFromDB(name, sqlQuery);
    }

    public User getEntityByEmail(String email) throws DAOException {
        String sqlQuery = "SELECT * FROM cinema.users WHERE email = ?;";
        return getUserFromDB(email, sqlQuery);
    }

    public boolean delete(Integer id) throws DAOException {
        String sqlQuery = "DELETE FROM `cinema`.`users` WHERE `id` = ?";
        boolean isSuccessfully = false;

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
                prepStatement.execute();
                isSuccessfully = true;
            } catch (SQLException e) {
                //TODO LOG
                e.printStackTrace();
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return isSuccessfully;
    }

    public boolean create(User entity) throws DAOException {
        String sqlQuery = "INSERT INTO `cinema`.`users` (`username`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";
        boolean isSuccessfully = false;

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
                prepStatement.setString(1, entity.getUsername());
                prepStatement.setString(2, entity.getEmail());
                prepStatement.setString(3, entity.getPassword());
                prepStatement.setString(4, entity.getRole().getString());
                try {
                    prepStatement.execute();
                    isSuccessfully = true;
                } catch (SQLIntegrityConstraintViolationException e) {
                    //TODO LOG
                    e.printStackTrace();
                    throw new DAOException(e.getMessage(), e);
                } catch (SQLException e) {
                    //TODO LOG
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                //TODO LOG
                e.printStackTrace();
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return isSuccessfully;
    }

    private User getUserFromDB(String usernameOrEmail, String sqlQuery) throws DAOException {
        User user = new User();
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
                prepStatement.setString(1, usernameOrEmail);
                user = extractResultSet(prepStatement);
            } catch (SQLException e) {
                //TODO LOG
                e.printStackTrace();
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return user;
    }

    private User extractResultSet(PreparedStatement prepStatement) throws DAOException {
        User user = new User();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            if (!resultSet.isBeforeFirst()) {
                throw new DAOException("No such entry in the DB");
            }
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setRole(Role.contains(resultSet.getString(5)));
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return user;
    }
}
