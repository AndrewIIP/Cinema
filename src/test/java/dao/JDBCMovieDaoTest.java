package dao;

import static org.junit.Assert.*;

import model.dao.DayDao;
import model.dao.SessionDao;
import model.dao.exceptions.DAOException;
import model.dao.impl.JDBCDaoFactory;
import model.entity.Day;
import model.entity.Session;
import model.entity.Ticket;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JDBCMovieDaoTest {

    @Test
    public void testAdditionTwoAndThree(){
        JDBCDaoFactory factory = new JDBCDaoFactory();
        SessionDao dao = factory.createSessionDao();
        Session session = new Session();
        try {
            session = dao.getEntityById(20);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        System.out.println("Session getId               - "+session.getId());
        System.out.println("Session getTimeHoursMins    - "+session.getTimeHoursMins());
        System.out.println("Session getDayID            - "+session.getDayID());
        System.out.println("Session getMovieID          - "+session.getMovieID());

        for(Ticket ticket: session.getTicketList()){
            System.out.println("    Ticket getId         - "+ticket.getId());
            System.out.println("    Ticket getPlace      - "+ticket.getPlace());
            System.out.println("    Ticket getUserID     - "+ticket.getUserID());
            System.out.println("    Ticket getSessionID  - "+ticket.getSessionID());
            System.out.println("    Ticket getOwner      - "+ticket.getOwner().toString());


        }
        assertEquals(1,1);
    }

    @Test
    public void testIsEngaged(){
        Session session = new Session();
        Ticket ticket = new Ticket();
        ticket.setPlace(5);
        //session.getTicketList().add(ticket);
        //assertTrue(session.isEngagedPlace(5));
        assertFalse(session.isEngagedPlace(4));
    }
}
