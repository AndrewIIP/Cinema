package model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Ticket {
    private int id;
    private int place;
    private int userID;
    private int sessionID;
    private User owner;
    private Session session;

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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean notEmpty(){
        return id != 0 &&
                place != 0 &&
                userID != 0 &&
                sessionID != 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                place == ticket.place &&
                userID == ticket.userID &&
                sessionID == ticket.sessionID &&
                Objects.equals(owner, ticket.owner) &&
                Objects.equals(session, ticket.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, userID, sessionID, owner, session);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", place=" + place +
                ", userID=" + userID +
                ", sessionID=" + sessionID +
                ", owner=" + owner +
                ", session=" + session +
                '}';
    }
}
