package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.SessionDao;
import model.dao.exceptions.DAOException;
import model.dao.mappers.*;
import model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        Map<Integer, User> userMap = new HashMap<>();

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

        try{
            prepStatement = connection.prepareStatement(sqlQuery);
            try {
                resultSet = prepStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    throw new DAOException("No such session with id (" + id + ") in the DB");
                }
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    session = sessionMapper.extractFromResultSet(resultSet, 1, 2, 3, 4);
                    session.setDay(dayMapper.makeUnique(daysMap, dayMapper.extractFromResultSet(resultSet, 5, 11, 12)));
                    session.setMovie(movieMapper.makeUnique(moviesMap, movieMapper.extractFromResultSet(resultSet, 6, 16, 7)));
                    session = sessionMapper.makeUnique(sessionsMap, session);

                    User user = userMapper.extractFromResultSet(resultSet, 21, 22, 23, 24, 25);

                    if(user != null){
                        ticket = ticketMapper.extractFromResultSet(resultSet, 17, 18, 19, 20);
                        ticket.setOwner(user);
                        ticket = ticketMapper.makeUnique(ticketMap, ticket);
                        Optional.ofNullable(ticket).ifPresent(session.getTicketList()::add);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();//TODO LOG
            } finally {
                try{
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();//TODO LOG
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); //TODO LOG
        } finally {
            try{
                if (prepStatement != null)
                    prepStatement.close();
            } catch (SQLException e){
                e.printStackTrace();//TODO LOG
            }
        }
        return session;
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
