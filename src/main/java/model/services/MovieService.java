package model.services;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.exceptions.DAOException;
import model.dao.impl.JDBCDaoFactory;
import model.entity.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class MovieService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Movie> getAllMovies(){
        try(MovieDao dao = daoFactory.createMovieDao()){
            return dao.getAll();
        }
    }

    public void removeMovieById(int movieId){
        try(MovieDao dao = daoFactory.createMovieDao()){
            try {
                dao.delete(movieId);
            } catch (DAOException e) {
                e.printStackTrace(); //TODO LOG
            }
        }
    }

    public void createMovie(Movie movie, String nameEng, String nameUkr){
        try(MovieDao dao = daoFactory.createMovieDao()){
            try {
                dao.getConnection().setAutoCommit(false);
                dao.create(movie);
                int movieId = dao.getIdByPictureName(movie.getPicUrl());
                dao.insertTranslatedNameById(movieId, 1, nameEng);
                dao.insertTranslatedNameById(movieId, 2, nameUkr);
                dao.getConnection().commit();
            } catch (DAOException | SQLException e) {
                e.printStackTrace(); //TODO LOG
            }
        }
    }

    public void setDaoLocale(Locale locale){
        daoFactory.setDaoLocale(locale);
    }
}
