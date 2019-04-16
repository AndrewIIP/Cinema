package model.entity;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Session {
    private int id;
    private Time time;
    private int dayID;
    private int movieID;
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

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
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

    public boolean isEngagedPlace(int placeNumber){
        Optional<Ticket> ticket = ticketList.stream().filter(a -> a.getPlace() == placeNumber).findFirst();
        return ticket.isPresent();
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
                dayID == session.dayID &&
                movieID == session.movieID &&
                Objects.equals(time, session.time) &&
                Objects.equals(day, session.day) &&
                Objects.equals(movie, session.movie) &&
                Objects.equals(ticketList, session.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, dayID, movieID, day, movie, ticketList);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", dayID=" + dayID +
                ", movieID=" + movieID +
                ", day=" + day +
                ", movie=" + movie +
                ", ticketList=" + ticketList +
                '}';
    }
}
