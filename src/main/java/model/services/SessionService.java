package model.services;

import model.dao.DaoFactory;
import model.dao.SessionDao;
import model.dao.exceptions.DAOException;
import model.entity.Session;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.util.Locale;

import static model.util.LogMsg.*;

public class SessionService {
    private Logger log = LogGen.getInstance();
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Session getSessionById(int id) throws DAOException {
        Session session;

        try (SessionDao dao = daoFactory.createSessionDao()) {
            session = dao.getEntityById(id);
            log.info(RECEIVED_SESSION_BY_ID);
        }
        return session;
    }

    public void removeSessionById(int id) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            dao.delete(id);
            log.info(SESSION_WAS_REMOVED);
        } catch (DAOException e) {
            log.error(CANT_REMOVE_SESSION);
        }
    }

    public void createSession(Session session) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            dao.create(session);
            log.info(SESSION_WAS_CREATED);
        } catch (DAOException e) {
            log.info(CANT_CREATE_SESSION);
        }
    }

    public void setDaoLocale(Locale locale) {
        daoFactory.setDaoLocale(locale);
        log.info(DAO_LOCALE_IS_SET + " for " + daoFactory.getClass().getSimpleName() + " as " + locale.toString());
    }
}
