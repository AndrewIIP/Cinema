package model.services;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.exceptions.DAOException;
import model.dao.impl.JDBCDaoFactory;
import model.entity.Movie;

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

    public void setDaoLocale(Locale locale){
        daoFactory.setDaoLocale(locale);
    }
}
