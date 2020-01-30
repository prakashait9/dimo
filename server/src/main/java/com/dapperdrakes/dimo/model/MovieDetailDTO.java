package com.dapperdrakes.dimo.model;

import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MovieDetailDTO {

    private int _id;
    private String origTitle;
    private Date releaseDate;
    private List<SpokenLang> spoken_lang;
    private Integer runtime;
    private List<Genre> genre;
    private String overview;
    private String poster;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<SpokenLang> getSpoken_lang() {
        return spoken_lang;
    }

    public void setSpoken_lang(List<SpokenLang> spoken_lang) {
        this.spoken_lang = spoken_lang;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public MovieDetailDTO() {}

    public MovieDetailDTO(Movie movie) {
        this._id = movie.getId();
        this.genre = movie.getGenre();
        this.spoken_lang = movie.getSpoken_lang();
        this.origTitle = movie.getOrigTitle();
        this.releaseDate = movie.getReleaseDate();
        this.runtime = movie.getRuntime();
        this.overview = movie.getOverview();
        this.poster = movie.getPoster();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDetailDTO that = (MovieDetailDTO) o;
        return _id == that._id &&
                Objects.equals(origTitle, that.origTitle) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(spoken_lang, that.spoken_lang) &&
                Objects.equals(runtime, that.runtime) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(poster, that.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, origTitle, releaseDate, spoken_lang, runtime, genre, overview, poster);
    }
}

