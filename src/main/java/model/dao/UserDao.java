package model.dao;

import model.dao.exceptions.DAOException;
import model.entity.User;

public interface UserDao extends GenericDao<User, Integer>{

    User getEntityByUsername(String name) throws DAOException;
    User getEntityByEmail(String name) throws DAOException;

}
