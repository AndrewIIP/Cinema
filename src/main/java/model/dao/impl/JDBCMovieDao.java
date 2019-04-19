package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.MovieDao;
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
import java.util.*;

public class JDBCMovieDao extends AbstractDao implements MovieDao {
    private Connection connection;

    public JDBCMovieDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Movie> getAll() {

        MovieMapper movieMapper = new MovieMapper();
        SessionMapper sessionMapper = new SessionMapper();
        DayMapper dayMapper = new DayMapper();

        Map<Integer, Movie> moviesMap = new HashMap<>();
        Map<Integer, Session> sessionsMap = new HashMap<>();

        String sqlQuery = "SELECT s.id,\n" +
                "       s.time,\n" +
                "       s.day_id,\n" +
                "       s.movie_id,\n" +
                "       m.id,\n" +
                "       lm.movie_name,\n" +
                "       m.pic_url,\n" +
                "       d.id,\n" +
                "       ld.day_name,\n" +
                "       ld.day_name_short\n" +
                "FROM cinema.movies AS m\n" +
                "       LEFT JOIN cinema.sessions AS s ON s.movie_id = m.id\n" +
                "       LEFT JOIN cinema.days AS d ON d.id = s.day_id\n" +
                "       LEFT JOIN cinema.days_translate as ld on d.id = ld.day_id\n" +
                "       LEFT JOIN cinema.movies_translate as lm ON m.id = lm.movie_id && (ld.lang_id = lm.lang_id || ld.lang_id IS NULL)\n" +
                "       LEFT JOIN cinema.languages as l ON l.id = lm.lang_id\n" +
                "WHERE l.lang_tag = \'" + super.getLocale() + "\'\n" +
                "ORDER BY d.id, s.time;";
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        Connection connection = this.connection;

        try{
            prepStatement = connection.prepareStatement(sqlQuery);
            try {
                resultSet = prepStatement.executeQuery();
                while (resultSet.next()) {

                    Movie movie = movieMapper.extractFromResultSet(resultSet, 5, 6 ,7);
                    movie = movieMapper.makeUnique(moviesMap, movie);

                    Session rowSession = sessionMapper.extractFromResultSet(resultSet, 1, 2, 3, 4);
                    Day day = dayMapper.extractFromResultSet(resultSet, 8, 9, 10);

                    Optional.ofNullable(day).ifPresent(rowSession::setDay);
                    rowSession.setMovie(movie);
                    rowSession = sessionMapper.makeUnique(sessionsMap, rowSession);
                    Movie finalMovie = movie;
                    Optional.ofNullable(rowSession).ifPresent(e -> finalMovie.getSessions().add(e));
                    /*
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt(5));
                    movie.setName(resultSet.getString(6));
                    movie.setPicUrl(resultSet.getString(7));

                    movie = movieMapper.makeUnique(moviesMap, movie);
                    Session rowSession = sessionMapper.extractFromResultSet(resultSet, 1, 2, 3, 4);

                    Day day = dayMapper.extractFromResultSet(resultSet, 8, 9, 10);
                    Optional.ofNullable(day).ifPresent(rowSession::setDay);
                    rowSession.setMovie(movie);
                    rowSession = sessionMapper.makeUnique(sessionsMap, rowSession);
                    Movie finalMovie = movie;
                    Optional.ofNullable(rowSession).ifPresent(e -> finalMovie.getSessions().add(e));
                    */
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
        return new LinkedList<>(moviesMap.values());
    }

    @Override
    public Movie update(Movie entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Movie getEntityById(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Movie entity) throws DAOException {
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
