package model.dao;

import model.entity.Ticket;

import java.util.List;

public interface TicketDao extends GenericDao<Ticket, Integer> {

    List<Ticket> getByUserId(int userId);

}
