package model.services;

import model.dao.DaoFactory;
import model.dao.TicketDao;
import model.dao.exceptions.DAOException;
import model.entity.Ticket;

import java.util.List;
import java.util.Locale;

public class TicketService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createTicket(Ticket ticket) throws DAOException {

        try (TicketDao dao = daoFactory.createTicketDao()) {
            dao.create(ticket);
        }
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.getByUserId(userId);
        }
    }

    public void removeTicket(Ticket ticket) {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            dao.delete(ticket.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void setDaoLocale(Locale locale) {
        daoFactory.setDaoLocale(locale);
    }
}
