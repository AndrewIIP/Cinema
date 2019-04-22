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
import model.util.LogGen;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import java.sql.*;
import java.util.*;

public class JDBCMovieDao extends AbstractDao implements MovieDao {
    private Connection connection;
    private Logger log = LogGen.getInstance();

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
            log.debug(PREP_STAT_OPENED + " in MovieDao getAll()");
            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in MovieDao getAll()");
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
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try{
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in MovieDao getAll()");
                } catch (SQLException e){
                    log.error(RESULT_SET_CANT_CLOSE, e);
                }
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try{
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in MovieDao getAll()");
            } catch (SQLException e){
                log.error(PREP_STAT_CANT_CLOSE, e);
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
        String sqlQuery = "DELETE FROM `cinema`.`movies` WHERE `id` = " + id;
        PreparedStatement prepStatement = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            log.debug(PREP_STAT_OPENED + " in MovieDao delete()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in MovieDao delete()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_DELETING, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in MovieDao delete()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
    }

    @Override
    public void create(Movie entity) throws DAOException {
        String sqlQuery = "INSERT INTO `cinema`.`movies` (`pic_url`) VALUES (?)";
        PreparedStatement prepStatement = null;

        Connection connection = this.connection;
        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setString(1, entity.getPicUrl());
            log.debug(PREP_STAT_OPENED + " in MovieDao create()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in MovieDao create()");
            } catch (SQLIntegrityConstraintViolationException e) {
                log.error(SQL_EXCEPTION_WHILE_INSERT, e);
                throw new DAOException(e.getMessage(), e);
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_INSERT, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in MovieDao create()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
    }

    @Override
    public void insertTranslatedNameById(int movieID, int languageID, String movieName) {
        String sqlQuery = "INSERT INTO `cinema`.`movies_translate` (`movie_id`, `lang_id`, `movie_name`) VALUES (?, ?, ?)";
        PreparedStatement prepStatement = null;
        Connection connection = this.connection;

        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setInt(1, movieID);
            prepStatement.setInt(2, languageID);
            prepStatement.setString(3, movieName);
            log.debug(PREP_STAT_OPENED + " in MovieDao insertTranslatedNameById()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in MovieDao insertTranslatedNameById()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        } finally {
            try {
                if (prepStatement != null)
                    prepStatement.close();
                log.debug(PREP_STAT_CLOSED + " in MovieDao insertTranslatedNameById()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
    }

    @Override
    public int getIdByPictureName(String picName) throws DAOException{
        int movieID = 0;
        String sqlQuery = "SELECT m.id FROM `cinema`.`movies` AS m WHERE pic_url = \'" + picName + "\' ORDER BY m.id DESC";
        Connection connection = this.connection;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        try {
            prepStatement = connection.prepareStatement(sqlQuery);
            log.debug(PREP_STAT_OPENED + " in MovieDao getIdByPictureName()");
            try {
                resultSet = prepStatement.executeQuery();
                log.debug(QUERY_EXECUTED + " in MovieDao getIdByPictureName()");
                if (!resultSet.isBeforeFirst()) {
                    log.info(NO_SUCH_ENTRY_IN_DB + " in MovieDao getIdByPictureName()");
                    throw new DAOException("No such entry in the DB");
                }
                resultSet.next();
                movieID = resultSet.getInt(1);
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    log.debug(RESULT_SET_CLOSED + " in MovieDao getIdByPictureName()");
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
                log.debug(PREP_STAT_CLOSED + " in MovieDao getIdByPictureName()");
            } catch (SQLException e) {
                log.error(PREP_STAT_CANT_CLOSE, e);
            }
        }
        return movieID;
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

    public Connection getConnection() {
        return connection;
    }
}
