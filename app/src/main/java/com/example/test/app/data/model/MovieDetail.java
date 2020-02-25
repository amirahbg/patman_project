package com.example.test.app.data.model;

import com.example.test.app.utils.Objects;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MovieDetail {
    @ColumnInfo(name = "title")
    @SerializedName("Title")
    private String title;

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    private String year;

    @ColumnInfo(name = "rated")
    @SerializedName("Rated")
    private String rated;

    @ColumnInfo(name = "released")
    @SerializedName("Released")
    private String released;

    @ColumnInfo(name = "runtime")
    @SerializedName("Runtime")
    private String runtime;

    @ColumnInfo(name = "genre")
    @SerializedName("Genre")
    private String genre;

    @ColumnInfo(name = "director")
    @SerializedName("Director")
    private String director;

    @ColumnInfo(name = "writer")
    @SerializedName("Writer")
    private String writer;

    @ColumnInfo(name = "actors")
    @SerializedName("Actors")
    private String actors;

    @ColumnInfo(name = "plot")
    @SerializedName("Plot")
    private String plot;

    @ColumnInfo(name = "language")
    @SerializedName("Language")
    private String language;

    @ColumnInfo(name = "country")
    @SerializedName("Country")
    private String country;

    @ColumnInfo(name = "awards")
    @SerializedName("Awards")
    private String awards;

    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    private String poster;

//    @SerializedName("Ratings")
//    private List<Rating> ratings;

    @ColumnInfo(name = "metascore")
    @SerializedName("Metascore")
    private String metascore;

    @ColumnInfo(name = "imdbRating")
    @SerializedName("imdbRating")
    private String imdbRating;


    @ColumnInfo(name = "imdbVotes")
    @SerializedName("imdbVotes")
    private String imdbVotes;

    @PrimaryKey
    @NonNull
    @SerializedName("imdbID")
    private String imdbID;

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    private String type;

    @ColumnInfo(name = "dvd")
    @SerializedName("DVD")
    private String dvd;

    @ColumnInfo(name = "boxOffice")
    @SerializedName("BoxOffice")
    private String boxOffice;

    @ColumnInfo(name = "production")
    @SerializedName("Production")
    private String production;

    @ColumnInfo(name = "website")
    @SerializedName("Website")
    private String website;

    @ColumnInfo(name = "response")
    @SerializedName("Response")
    private String response;

    public MovieDetail(String title, String year, String rated, String released, String runtime,
                       String genre, String director, String writer, String actors, String plot,
                       String language, String country, String awards, String poster,
                       String metascore, String imdbRating, String imdbVotes,
                       @NonNull String imdbID, String type, String dvd, String boxOffice,
                       String production, String website, String response) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.type = type;
        this.dvd = dvd;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
        this.response = response;
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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @NonNull
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(@NonNull String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDetail that = (MovieDetail) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(year, that.year) &&
                Objects.equals(rated, that.rated) &&
                Objects.equals(released, that.released) &&
                Objects.equals(runtime, that.runtime) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(director, that.director) &&
                Objects.equals(writer, that.writer) &&
                Objects.equals(actors, that.actors) &&
                Objects.equals(plot, that.plot) &&
                Objects.equals(language, that.language) &&
                Objects.equals(country, that.country) &&
                Objects.equals(awards, that.awards) &&
                Objects.equals(poster, that.poster) &&
                Objects.equals(metascore, that.metascore) &&
                Objects.equals(imdbRating, that.imdbRating) &&
                Objects.equals(imdbVotes, that.imdbVotes) &&
                Objects.equals(imdbID, that.imdbID) &&
                Objects.equals(type, that.type) &&
                Objects.equals(dvd, that.dvd) &&
                Objects.equals(boxOffice, that.boxOffice) &&
                Objects.equals(production, that.production) &&
                Objects.equals(website, that.website) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, rated, released, runtime, genre, director, writer,
                actors, plot, language, country, awards, poster, metascore, imdbRating, imdbVotes
                , imdbID, type, dvd, boxOffice, production, website, response);
    }
}
