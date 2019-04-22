package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.SessionDao;
import model.dao.exceptions.DAOException;
import model.dao.mappers.*;
import model.entity.*;
import model.util.LogGen;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import java.sql.*;
import java.util.*;

public class JDBCSessionDao extends AbstractDao implements SessionDao {
    private Connection connection;
    private Logger log = LogGen.getInstance();

    public JDBCSessionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Session> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Session update(Session entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Session getEntityById(Integer id) throws DAOException {
        Session session = new Session();

        DayMapper dayMapper = new DayMapper();
        MovieMapper movieMapper = new MovieMapper();
        SessionMapper sessionMapper = new SessionMapper();
        TicketMapper ticketMapper = new TicketMapper();
        UserMapper userMapper = new UserMapper();

        Map<Integer, Day> daysMap = new HashMap<>();
        Map<Integer, Movie> moviesMap = new HashMap<>();
        Map<Integer, Session> sessionsMap = new HashMap<>();
        Map<Integer, Ticket> ticketMap = new HashMap<>();

        String sqlQuery = "SELECT *\n" +
                "FROM cinema.sessions AS s\n" +
                "       LEFT JOIN cinema.days AS d ON s.day_id = d.id\n" +
                "       LEFT JOIN cinema.movies AS m ON m.id = s.movie_id\n" +
                "       LEFT JOIN cinema.days_translate as dt on dt.day_id = d.id\n" +
                "       LEFT JOIN cinema.movies_translate as mt on mt.movie_id = m.id\n" +
                "       LEFT JOIN cinema.tickets AS t ON t.showtime_id = s.id\n" +
                "       LEFT JOIN cinema.users AS u ON u.id = t.user_id\n" +
                "       LEFT JOIN cinema.languages as l ON l.id = mt.lang_id && l.id = dt.lang_id\n" +
                "WHERE l.lang_tag = \'" + super.getLocale() + "\' && s.id = " + id + "\n" +
                "ORDER BY t.place;";
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Connection connection = this.connection;

        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            log.debug(PREP_STAT_OPENED + " in SessionDao getEntityById()");
            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in SessionDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    log.info(NO_SUCH_ENTRY_IN_DB + "in SessionDao getEntityById()");
                    throw new DAOException("No such session with id (" + id + ") in the DB");
                }

                while (resultSet.next()) {
                    Ticket ticket;
                    session = sessionMapper.extractFromResultSet(resultSet, 1, 2, 3, 4);
                    session.setDay(dayMapper.makeUnique(daysMap, dayMapper.extractFromResultSet(resultSet, 5, 11, 12)));
                    session.setMovie(movieMapper.makeUnique(moviesMap, movieMapper.extractFromResultSet(resultSet, 6, 16, 7)));
                    session = sessionMapper.makeUnique(sessionsMap, session);

                    User user = userMapper.extractFromResultSet(resultSet, 21, 22, 23, 24, 25);

                    if (user != null) {
                        ticket = ticketMapper.extractFromResultSet(resultSet, 17, 18, 19, 20);
                        ticket.setOwner(user);
                        ticket.setSession(session);
                        ticket = ticketMapper.makeUnique(ticketMap, ticket);
                        Optional.ofNullable(ticket).ifPresent(session.getTicketList()::add);
                    }
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in SessionDao getEntityById()");
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
                log.debug(PREP_STAT_CLOSED + " in SessionDao getEntityById()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
        return session;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        String sqlQuery = "DELETE FROM `cinema`.`sessions` WHERE `id` = " + id;
        PreparedStatement prepStatement = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            log.debug(PREP_STAT_OPENED + " in SessionDao delete()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in SessionDao delete()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_DELETING, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in SessionDao delete()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
    }

    @Override
    public void create(Session entity) throws DAOException {
        String sqlQuery = "INSERT INTO `cinema`.`sessions` (`time`, `day_id`, `movie_id`) VALUES (?, ?, ?)";
        PreparedStatement prepStatement = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setTime(1, entity.getTime());
            prepStatement.setInt(2, entity.getDayID());
            prepStatement.setInt(3, entity.getMovieID());
            log.debug(PREP_STAT_OPENED + " in SessionDao create()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in SessionDao create()");
            } catch (SQLIntegrityConstraintViolationException e) {
                log.error(CANT_CREATE_SESSION, e);
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
                log.debug(PREP_STAT_CLOSED + " in SessionDao create()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
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
