package com.example.moviedb.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Movie {
    @Id
    private Long id;
    private String originalTitle;
    private String genres;
    private Double popularity;
    private String releaseDate;
    private Double voteAverage;
    private String overview;
    @Generated(hash = 483575406)
    public Movie(Long id, String originalTitle, String genres, Double popularity,
            String releaseDate, Double voteAverage, String overview) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.genres = genres;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }
    @Generated(hash = 1263461133)
    public Movie() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOriginalTitle() {
        return this.originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getGenres() {
        return this.genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public Double getPopularity() {
        return this.popularity;
    }
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public String getReleaseDate() {
        return this.releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Double getVoteAverage() {
        return this.voteAverage;
    }
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
    public String getOverview() {
        return this.overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }



}
