package model.entity;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Session {
    private int id;
    private Time time;
    private int day_id;
    private int movie_id;
    private Day day;
    private Movie movie;
    private String timeHoursMins;
    private List<Ticket> ticketList = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
        setTimeHoursMins(this.timeHoursMins = time.toString().substring(0,time.toString().lastIndexOf(':')));
    }

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public void setTimeHoursMins(String timeHoursMins) {
        this.timeHoursMins = timeHoursMins;
    }

    public String getTimeHoursMins(){
        return timeHoursMins;
    }

    public boolean notEmpty(){
        return  time != null &&
                day != null &&
                movie != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id &&
                day_id == session.day_id &&
                movie_id == session.movie_id &&
                Objects.equals(time, session.time) &&
                Objects.equals(day, session.day) &&
                Objects.equals(movie, session.movie) &&
                Objects.equals(ticketList, session.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, day_id, movie_id, day, movie, ticketList);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", day_id=" + day_id +
                ", movie_id=" + movie_id +
                ", day=" + day +
                ", movie=" + movie +
                ", ticketList=" + ticketList +
                '}';
    }
}
