package dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import model.dao.DayDao;
import model.dao.MovieDao;
import model.dao.exceptions.DAOException;
import model.dao.impl.JDBCDaoFactory;
import model.dao.impl.JDBCMovieDao;
import model.entity.Day;
import model.entity.Movie;
import model.entity.Session;
import org.junit.Test;

public class JDBCMovieDaoTest {

    @Test
    public void testAdditionTwoAndThree(){
        JDBCDaoFactory factory = new JDBCDaoFactory();
        DayDao dao = factory.createDayDao();
        Day day = new Day();
        try {
            day = dao.getEntityById(5);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        for(Session session : day.getSessions()){
            System.out.println("Session getId                - "+session.getId());
            System.out.println("Session getTimeHoursMins     - "+session.getTimeHoursMins());
            System.out.println("Session getDay_id            - "+session.getDay_id());
            System.out.println("Session getMovie_id          - "+session.getMovie_id());
            System.out.println("    Day getId                - "+session.getDay().getId());
            System.out.println("    Day getName              - "+session.getDay().getName());
            System.out.println("    Day getShortName         - "+session.getDay().getShortName());
            System.out.println("    Movie getId              - "+session.getMovie().getId());
            System.out.println("    Movie getName            - "+session.getMovie().getName());
            System.out.println("    Movie getPicUrl          - "+session.getMovie().getPicUrl());


        }

        assertEquals(1,1);
    }
}
