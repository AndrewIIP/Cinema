package model.dao;

import model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<User, Integer, String>{
    public List<User> getAll() {
        return null;
    }

    public User update(User entity) {
        return null;
    }

    public User getEntityById(Integer id) {
        return null;
    }

    @Override
    public User getEntityByName(String name) {
        User tempUser = new User();
        PreparedStatement statement = getPrepareStatement("select * from users where username = ?");
        try {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getEntityByEmail(String email) {
        return null;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public boolean create(User entity) {
        PreparedStatement ps = getPrepareStatement("INSERT INTO users VALUES (?, ?, ?)");
        try {
            ps.setString(1, "vaha");
            ps.setString(2, "super@mail.com");
            ps.setString(3, "superpass");
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
