package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.UserDao;
import model.dao.exceptions.DAOException;
import model.dao.mappers.*;
import model.entity.*;
import model.util.Role;

import java.sql.*;
import java.util.*;

public class JDBCUserDao extends AbstractDao implements UserDao {
    Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>(20);
        String sqlQuery = "SELECT * FROM cinema.users;";
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            try {
                resultSet = prepStatement.executeQuery();
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
                e.printStackTrace();//TODO LOG
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();//TODO LOG
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); //TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            }
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
        PreparedStatement prepStatement = null;
        Connection connection = this.connection;

        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setInt(1, entity.getId());
            prepStatement.setString(2, entity.getUsername());
            prepStatement.setString(3, entity.getEmail());
            prepStatement.setString(4, entity.getPassword());
            prepStatement.setString(5, entity.getRole().getString());
            prepStatement.setInt(6, entity.getId());
            try {
                prepStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace(); //TODO LOG
            }
        } catch (SQLException e) {
            e.printStackTrace(); //TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(); //TODO LOG
            }
        }
        return entity;
    }

    public User getEntityById(Integer id) throws DAOException {
        String sqlQuery = "SELECT * FROM cinema.users WHERE id = ?;";
        User user = new User();
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setInt(1, id);
            try {
                resultSet = prepStatement.executeQuery();
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
                e.printStackTrace();//TODO LOG
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();//TODO LOG
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();//TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            }
        }
        return user;
    }

    public User getEntityByUsername(String name) throws DAOException {
        String sqlQuery = "SELECT *\n" +
                "FROM cinema.users AS u\n" +
                "       LEFT JOIN cinema.tickets AS t ON t.user_id = u.id\n" +
                "       LEFT JOIN cinema.sessions AS s ON t.showtime_id = s.id\n" +
                "       LEFT JOIN cinema.movies AS m ON s.movie_id = m.id\n" +
                "       LEFT JOIN cinema.days AS d ON d.id = s.day_id\n" +
                "       LEFT JOIN cinema.days_translate as dt on dt.day_id = d.id\n" +
                "       LEFT JOIN cinema.movies_translate as mt on mt.movie_id = m.id\n" +
                "       LEFT JOIN cinema.languages as l ON l.id = mt.lang_id && l.id = dt.lang_id\n" +
                "WHERE u.username = \'" + name + "\' && l.lang_tag = \'" + super.getLocale() + "\' || " +
                "u.username = \'" + name + "\' && t.id IS NULL\n" +
                "ORDER BY d.id, s.time;";
        return getUserFromDB(name, sqlQuery);
    }

    public User getEntityByEmail(String email) throws DAOException {
        String sqlQuery = "SELECT *\n" +
                "FROM cinema.users AS u\n" +
                "       LEFT JOIN cinema.tickets AS t ON t.user_id = u.id\n" +
                "       LEFT JOIN cinema.sessions AS s ON t.showtime_id = s.id\n" +
                "       LEFT JOIN cinema.movies AS m ON s.movie_id = m.id\n" +
                "       LEFT JOIN cinema.days AS d ON d.id = s.day_id\n" +
                "       LEFT JOIN cinema.days_translate as dt on dt.day_id = d.id\n" +
                "       LEFT JOIN cinema.movies_translate as mt on mt.movie_id = m.id\n" +
                "       LEFT JOIN cinema.languages as l ON l.id = mt.lang_id && l.id = dt.lang_id\n" +
                "WHERE u.email = \'" + email + "\' && l.lang_tag = \'" + super.getLocale() + "\' || " +
                "u.email = \'" + email + "\' && t.id IS NULL\n" +
                "ORDER BY d.id, s.time;";
        return getUserFromDB(email, sqlQuery);
    }

    public void delete(Integer id) throws DAOException {
        String sqlQuery = "DELETE FROM `cinema`.`users` WHERE `id` = ?";
        PreparedStatement prepStatement = null;


        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();//TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            }
        }
    }

    public void create(User entity) throws DAOException {
        String sqlQuery = "INSERT INTO `cinema`.`users` (`username`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";
        PreparedStatement prepStatement = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setString(1, entity.getUsername());
            prepStatement.setString(2, entity.getEmail());
            prepStatement.setString(3, entity.getPassword());
            prepStatement.setString(4, entity.getRole().getString());
            try {
                prepStatement.execute();
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();//TODO LOG
                throw new DAOException(e.getMessage(), e);
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            }
        } catch (SQLException e) {
            e.printStackTrace();//TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private User getUserFromDB(String usernameOrEmail, String sqlQuery) throws DAOException {
        User user = new User();

        UserMapper userMapper = new UserMapper();
        TicketMapper ticketMapper = new TicketMapper();
        SessionMapper sessionMapper = new SessionMapper();
        MovieMapper movieMapper = new MovieMapper();
        DayMapper dayMapper = new DayMapper();

        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Ticket> ticketMap = new HashMap<>();
        Map<Integer, Session> sessionMap = new HashMap<>();
        Map<Integer, Movie> movieMap = new HashMap<>();
        Map<Integer, Day> dayMap = new HashMap<>();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Connection connection = this.connection;

        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            try {
                resultSet = prepStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    throw new DAOException("No such entry in the DB");
                }
                while (resultSet.next()) {

                    user = userMapper.extractFromResultSet(resultSet, 1, 2, 4, 3, 5);
                    Ticket ticket = ticketMapper.extractFromResultSet(resultSet, 6, 7, 8, 9);
                    Session session = sessionMapper.extractFromResultSet(resultSet, 10, 11, 12, 13);
                    Day day = dayMapper.extractFromResultSet(resultSet, 16, 20, 21);
                    Movie movie = movieMapper.extractFromResultSet(resultSet, 14, 25, 15);

                    user = userMapper.makeUnique(userMap, user);

                    Optional.ofNullable(ticket).ifPresent((a) -> a = ticketMapper.makeUnique(ticketMap, a));
                    Optional.ofNullable(day).ifPresent((a) -> a = dayMapper.makeUnique(dayMap, a));
                    Optional.ofNullable(movie).ifPresent((a) -> a = movieMapper.makeUnique(movieMap, a));

//                    ticket = ticketMapper.makeUnique(ticketMap, ticket);
//                    day = dayMapper.makeUnique(dayMap, day);
//                    movie = movieMapper.makeUnique(movieMap, movie);

                    session.setDay(day);
                    session.setMovie(movie);
                    Optional.ofNullable(session).ifPresent((a) -> a = sessionMapper.makeUnique(sessionMap, a));
                    Optional.ofNullable(ticket).ifPresent((a) -> ticket.setSession(session));

                    user.getUserTickets().add(ticket);

                }
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();//TODO LOG
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();//TODO LOG
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            }
        }
        return user;
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
