package com.example.test.app.data.model;

import com.example.test.app.utils.Objects;
import com.google.gson.annotations.SerializedName;

/**
 * this class is for representing movie items without too many details
 */
public class MovieItem {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    public MovieItem(String title, String year, String imdbId, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        MovieItem movieItem = (MovieItem) o;
        return Objects.equals(title, movieItem.title) &&
                Objects.equals(year, movieItem.year) &&
                Objects.equals(imdbId, movieItem.imdbId) &&
                Objects.equals(type, movieItem.type) &&
                Objects.equals(poster, movieItem.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, imdbId, type, poster);
    }
}
