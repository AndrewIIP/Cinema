package model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Movie {
    private int id;
    private String name;
    private String picUrl;
    private List<Session> sessions = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(picUrl, movie.picUrl) &&
                Objects.equals(sessions, movie.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, picUrl, sessions);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", sessions=" + sessions +
                '}';
    }
}
