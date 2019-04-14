package model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Ticket {
    private int id;
    private int place;
    private int user_id;
    private int session_id;
    private User owner;
    private List<Ticket> ticketList = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                place == ticket.place &&
                user_id == ticket.user_id &&
                session_id == ticket.session_id &&
                Objects.equals(owner, ticket.owner) &&
                Objects.equals(ticketList, ticket.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, user_id, session_id, owner, ticketList);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", place=" + place +
                ", user_id=" + user_id +
                ", session_id=" + session_id +
                ", owner=" + owner +
                ", ticketList=" + ticketList +
                '}';
    }
}
