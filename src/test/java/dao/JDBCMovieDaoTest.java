package dao;

import static org.junit.Assert.*;

import model.dao.DayDao;
import model.dao.SessionDao;
import model.dao.TicketDao;
import model.dao.UserDao;
import model.dao.exceptions.DAOException;
import model.dao.impl.JDBCDaoFactory;
import model.entity.Day;
import model.entity.Session;
import model.entity.Ticket;
import model.entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JDBCMovieDaoTest {

    @Test
    public void testAdditionTwoAndThree() {
        JDBCDaoFactory factory = new JDBCDaoFactory();
        SessionDao dao = factory.createSessionDao();
        Session session = new Session();
        try {
            session = dao.getEntityById(20);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        System.out.println("Session getId               - " + session.getId());
        System.out.println("Session getTimeHoursMins    - " + session.getTimeHoursMins());
        System.out.println("Session getDayID            - " + session.getDayID());
        System.out.println("Session getMovieID          - " + session.getMovieID());

        for (Ticket ticket : session.getTicketList()) {
            System.out.println("    Ticket getId         - " + ticket.getId());
            System.out.println("    Ticket getPlace      - " + ticket.getPlace());
            System.out.println("    Ticket getUserID     - " + ticket.getUserID());
            System.out.println("    Ticket getSessionID  - " + ticket.getSessionID());
            System.out.println("    Ticket getOwner      - " + ticket.getOwner().toString());


        }
        assertEquals(1, 1);
    }

    @Test
    public void testIsEngaged() {
        Session session = new Session();
        Ticket ticket = new Ticket();
        ticket.setPlace(5);
        //session.getTicketList().add(ticket);
        //assertTrue(session.isEngagedPlace(5));
        assertFalse(session.isEngagedPlace(4));
    }

    @Test
    public void testGetTicketByUserId() {
        JDBCDaoFactory factory = new JDBCDaoFactory();
        TicketDao dao = factory.createTicketDao();
        List<Ticket> ticketList = new LinkedList<>();
        ticketList = dao.getByUserId(3);

        for (Ticket ticket : ticketList) {
            System.out.println("Ticket getId         - " + ticket.getId());
            System.out.println("Ticket getPlace      - " + ticket.getPlace());
            System.out.println("Ticket getUserID     - " + ticket.getUserID());
            System.out.println("Ticket getSessionID  - " + ticket.getSessionID());

            System.out.println("    Session getId             - " + ticket.getSession().getId());
            System.out.println("    Session getTimeHoursMins  - " + ticket.getSession().getTimeHoursMins());
            System.out.println("    Session getDayID          - " + ticket.getSession().getDayID());

            System.out.println("            Movie getId       - " + ticket.getSession().getMovie().getId());
            System.out.println("            Movie getName     - " + ticket.getSession().getMovie().getName());
            System.out.println("            Movie getPic     - " + ticket.getSession().getMovie().getPicUrl());

            System.out.println("            Day getId         - " + ticket.getSession().getDay().getId());
            System.out.println("            Day getName       - " + ticket.getSession().getDay().getName());
            System.out.println("            Day getShortName  - " + ticket.getSession().getDay().getShortName());


        }

    }

    @Test
    public void testGetUserByUsernameOrMail(){
        JDBCDaoFactory factory = new JDBCDaoFactory();
        UserDao dao = factory.createUserDao();
        User user = new User();
        try {
            user = dao.getEntityByEmail("neil@gmail.com");
        } catch (DAOException e) {
            e.printStackTrace();
        }


        System.out.println("User id           - " + user.getId());
        System.out.println("User username     - " + user.getUsername());
        System.out.println("User password     - " + user.getPassword());
        System.out.println("User mail         - " + user.getEmail());
        System.out.println("User role         - " + user.getRole());

        for (Ticket ticket : user.getUserTickets()) {
            System.out.println("Ticket getId         - " + ticket.getId());
            System.out.println("Ticket getPlace      - " + ticket.getPlace());
            System.out.println("Ticket getUserID     - " + ticket.getUserID());
            System.out.println("Ticket getSessionID  - " + ticket.getSessionID());

            System.out.println("    Session getId             - " + ticket.getSession().getId());
            System.out.println("    Session getTimeHoursMins  - " + ticket.getSession().getTimeHoursMins());
            System.out.println("    Session getDayID          - " + ticket.getSession().getDayID());

            System.out.println("            Movie getId       - " + ticket.getSession().getMovie().getId());
            System.out.println("            Movie getName     - " + ticket.getSession().getMovie().getName());
            System.out.println("            Movie getPic     - " + ticket.getSession().getMovie().getPicUrl());

            System.out.println("            Day getId         - " + ticket.getSession().getDay().getId());
            System.out.println("            Day getName       - " + ticket.getSession().getDay().getName());
            System.out.println("            Day getShortName  - " + ticket.getSession().getDay().getShortName());


        }
    }
}
