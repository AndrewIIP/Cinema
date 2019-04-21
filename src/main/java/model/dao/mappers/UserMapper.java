package model.dao.mappers;

import model.entity.User;
import model.util.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException, IllegalArgumentException {
        User user = new User();
        user.setId(resultSet.getInt(columnIndexes[0]));
        user.setUsername(resultSet.getString(columnIndexes[1]));
        user.setPassword(resultSet.getString(columnIndexes[2]));
        user.setEmail(resultSet.getString(columnIndexes[3]));
        String role = resultSet.getString(columnIndexes[4]);
        Optional.ofNullable(role).ifPresent(a -> user.setRole(Role.contains(role)));
        return user.notEmpty() ? user : null;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
