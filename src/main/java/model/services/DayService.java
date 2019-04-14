package model.services;

import model.dao.DaoFactory;
import model.dao.DayDao;
import model.dao.MovieDao;
import model.dao.exceptions.DAOException;
import model.entity.Day;
import model.entity.Movie;

import java.util.List;
import java.util.Locale;

public class DayService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Day getDayById(int id){
        Day day = new Day();

        try(DayDao dao = daoFactory.createDayDao()){
            day = dao.getEntityById(id);
        } catch (DAOException e){
            e.printStackTrace(); //TODO LOG
        }
        return day;
    }

    public void setDaoLocale(Locale locale){
        daoFactory.setDaoLocale(locale);
    }
}
