package model.services;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.exceptions.DAOException;
import model.entity.Movie;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import static model.util.LogMsg.*;


public class MovieService {
    private Logger log = LogGen.getInstance();
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Movie> getAllMovies() {
        try (MovieDao dao = daoFactory.createMovieDao()) {
            log.info(ALL_MOVIES_RECEIVED);
            return dao.getAll();
        }
    }

    public void removeMovieById(int movieId) {
        try (MovieDao dao = daoFactory.createMovieDao()) {
            try {
                dao.delete(movieId);
                log.info(MOVIE_REMOVED);
            } catch (DAOException e) {
                log.error(CANT_REMOVE_MOVIE, e);
            }
        }
    }

    public void createMovie(Movie movie, String nameEng, String nameUkr) {
        try (MovieDao dao = daoFactory.createMovieDao()) {
            try {
                dao.getConnection().setAutoCommit(false);
                dao.create(movie);
                int movieId = dao.getIdByPictureName(movie.getPicUrl());
                dao.insertTranslatedNameById(movieId, 1, nameEng);
                dao.insertTranslatedNameById(movieId, 2, nameUkr);
                dao.getConnection().commit();
                log.info(MOVIE_CREATED);
            } catch (DAOException | SQLException e) {
                log.error(CANT_CREATE_MOVIE, e);
            }
        }
    }

    public void setDaoLocale(Locale locale) {
        daoFactory.setDaoLocale(locale);
        log.info(DAO_LOCALE_IS_SET + " for " + daoFactory.getClass().getSimpleName() + " as " + locale.toString());
    }
}
