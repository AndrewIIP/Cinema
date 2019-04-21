package model.dao;

import model.dao.exceptions.DAOException;
import model.entity.Movie;

import java.sql.Connection;

public interface MovieDao extends GenericDao<Movie, Integer> {

    void insertTranslatedNameById(int movieID, int languageID, String movieName);
    int getIdByPictureName(String picName) throws DAOException;
    Connection getConnection();

}
