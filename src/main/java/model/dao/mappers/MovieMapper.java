package model.dao.mappers;

import model.dao.exceptions.DAOException;
import model.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MovieMapper implements ObjectMapper<Movie> {
    @Override
    public Movie extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        Movie movie = new Movie();

        movie.setId(resultSet.getInt(columnIndexes[0]));
        movie.setName(resultSet.getString(columnIndexes[1]));
        movie.setPicUrl(resultSet.getString(columnIndexes[2]));

        return movie;
    }

    @Override
    public Movie makeUnique(Map<Integer, Movie> cache, Movie entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
