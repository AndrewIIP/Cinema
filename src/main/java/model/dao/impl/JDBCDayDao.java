package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.DayDao;
import model.dao.exceptions.DAOException;
import model.dao.mappers.DayMapper;
import model.dao.mappers.MovieMapper;
import model.dao.mappers.SessionMapper;
import model.entity.Day;
import model.entity.Movie;
import model.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCDayDao extends AbstractDao implements DayDao {
    Connection connection;

    public JDBCDayDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Day> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Day update(Day entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Day getEntityById(Integer id) throws DAOException {
        Day day = new Day();

        MovieMapper movieMapper = new MovieMapper();
        SessionMapper sessionMapper = new SessionMapper();
        DayMapper dayMapper = new DayMapper();

        Map<Integer, Movie> moviesMap = new HashMap<>();
        Map<Integer, Session> sessionsMap = new HashMap<>();
        Map<Integer, Day> daysMap = new HashMap<>();

        String sqlQuery = "SELECT d.id,\n" +
                "       dt.day_name,\n" +
                "       dt.day_name_short,\n" +
                "       s.id,\n" +
                "       s.time,\n" +
                "       s.day_id,\n" +
                "       s.movie_id,\n" +
                "       m.id,\n" +
                "       mt.movie_name,\n" +
                "       m.pic_url\n" +
                "FROM cinema.days AS d\n" +
                "       LEFT JOIN cinema.sessions AS s ON s.day_id = d.id\n" +
                "       LEFT JOIN cinema.movies AS m ON m.id = s.movie_id\n" +
                "       LEFT JOIN cinema.movies_translate AS mt ON mt.movie_id = m.id\n" +
                "       LEFT JOIN cinema.days_translate as dt on dt.day_id = d.id\n" +
                "       LEFT JOIN cinema.languages as l ON l.id = mt.lang_id && l.id = dt.lang_id\n" +
                "WHERE l.lang_tag = \'" + super.getLocale() + "\' && d.id = " + id + "\n" +
                "ORDER BY s.time;";
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Connection connection = this.connection;

        try{
            prepStatement = connection.prepareStatement(sqlQuery);
            try {
                resultSet = prepStatement.executeQuery();
                while (resultSet.next()) {
                    day = dayMapper.extractFromResultSet(resultSet, 1, 2, 3);
                    day = dayMapper.makeUnique(daysMap, day);
                    Movie movie = movieMapper.extractFromResultSet(resultSet, 8, 9, 10);
                    movie = movieMapper.makeUnique(moviesMap, movie);
                    Session session = sessionMapper.extractFromResultSet(resultSet, 4, 5, 6, 7);
                    session.setDay(day);
                    session.setMovie(movie);
                    session = sessionMapper.makeUnique(sessionsMap, session);
                    day.getSessions().add(session);
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
       return day;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Day entity) throws DAOException {
        throw new UnsupportedOperationException();
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
