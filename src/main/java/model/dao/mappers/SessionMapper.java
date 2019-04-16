package model.dao.mappers;

import model.entity.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

public class SessionMapper implements ObjectMapper<Session> {

    @Override
    public Session extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        Session session = new Session();
        TimeZone.setDefault(TimeZone.getTimeZone("GTM0"));

        session.setId(resultSet.getInt(columnIndexes[0]));
        Optional.ofNullable(resultSet.getTime(columnIndexes[1])).ifPresent(session::setTime);
        session.setDayID(resultSet.getInt(columnIndexes[2]));
        session.setMovieID(resultSet.getInt(columnIndexes[3]));

        return session;
    }

    @Override
    public Session makeUnique(Map<Integer, Session> cache, Session entity) {
        if(entity.notEmpty()){
            cache.putIfAbsent(entity.getId(), entity);
        }
        return cache.get(entity.getId());
    }
}
