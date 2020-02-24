package com.example.test.app.data.model;

import com.example.test.app.utils.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * this class is for wrapping the response of {@code MovieItem}}
 */
public class MovieItemResponse {
    @SerializedName("Search")
    private List<MovieItem> movieItems;

    @SerializedName("totalResults")
    private String totalResult;

    @SerializedName("Response")
    private String response;

    public MovieItemResponse(List<MovieItem> movieItems, String totalResult, String response) {
        this.movieItems = movieItems;
        this.totalResult = totalResult;
        this.response = response;
    }

    public List<MovieItem> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
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
        MovieItemResponse that = (MovieItemResponse) o;
        return Objects.equals(movieItems, that.movieItems) &&
                Objects.equals(totalResult, that.totalResult) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieItems, totalResult, response);
    }
}
