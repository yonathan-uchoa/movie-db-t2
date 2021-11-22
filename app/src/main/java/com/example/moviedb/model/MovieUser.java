package com.example.moviedb.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MovieUser {
    @Id
    private Long id;
    private Long userId;
    private Long movieId;

    private String criticizes;
    private double note;
    private boolean watched;
    @Generated(hash = 2064245860)
    public MovieUser(Long id, Long userId, Long movieId, String criticizes,
            double note, boolean watched) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.criticizes = criticizes;
        this.note = note;
        this.watched = watched;
    }
    @Generated(hash = 388136563)
    public MovieUser() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMovieId() {
        return this.movieId;
    }
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
    public String getCriticizes() {
        return this.criticizes;
    }
    public void setCriticizes(String criticizes) {
        this.criticizes = criticizes;
    }
    public double getNote() {
        return this.note;
    }
    public void setNote(double note) {
        this.note = note;
    }
    public boolean getWatched() {
        return this.watched;
    }
    public void setWatched(boolean watched) {
        this.watched = watched;
    }

}
