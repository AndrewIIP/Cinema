package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.UserDao;
import model.dao.exceptions.DAOException;
import model.dao.mappers.*;
import model.entity.*;
import model.util.LogGen;
import model.util.Role;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import java.sql.*;
import java.util.*;

public class JDBCUserDao extends AbstractDao implements UserDao {
    private Connection connection;
    private Logger log = LogGen.getInstance();

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
            log.debug(PREP_STAT_OPENED + " in UserDao getAll()");

            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in UserDao getAll()");

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
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in UserDao getAll()");
                } catch (SQLException e) {
                    log.error(RESULT_SET_CANT_CLOSE, e);
                }
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao getAll()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
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
            log.debug(PREP_STAT_OPENED + " in UserDao update()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in UserDao update()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_UPDATE, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao update()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
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
            log.debug(PREP_STAT_OPENED + " in UserDao getEntityById()");

            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in UserDao getEntityById()");

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
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in UserDao getEntityById()");
                } catch (SQLException e) {
                    log.error(RESULT_SET_CANT_CLOSE, e);
                }
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao getEntityById()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
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
            log.debug(PREP_STAT_OPENED + " in UserDao delete()");
            prepStatement.execute();
            log.debug(QUERY_EXECUTED + " in UserDao delete()");
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_WHILE_DELETING, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao delete()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
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
            log.debug(PREP_STAT_OPENED + " in UserDao create()");

            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in UserDao create()");
            } catch (SQLIntegrityConstraintViolationException e) {
                log.info(SUCH_USER_ALREADY_EXISTS, e);
                throw new DAOException(e.getMessage(), e);
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_CREATE, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao create()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
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
            log.debug(PREP_STAT_OPENED + " in UserDao getUserFromDB()");

            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in UserDao getUserFromDB()");

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

                    session.setDay(day);
                    session.setMovie(movie);
                    Optional.ofNullable(session).ifPresent((a) -> a = sessionMapper.makeUnique(sessionMap, a));
                    Optional.ofNullable(ticket).ifPresent((a) -> ticket.setSession(session));

                    user.getUserTickets().add(ticket);

                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in UserDao getUserFromDB()");

                } catch (SQLException e) {
                    log.error(RESULT_SET_CANT_CLOSE, e);
                }
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in UserDao getUserFromDB()");

            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
        return user;
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.debug(CONNECTION_CLOSED);
        } catch (SQLException e) {
            log.error(CANT_CLOSE_CONNECTION, e);
        }
    }

}
