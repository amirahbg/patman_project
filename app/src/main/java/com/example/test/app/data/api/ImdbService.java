package com.example.test.app.data.api;

import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.data.model.MovieItemResponse;
import com.example.test.app.utils.ApiResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImdbService {
    @GET("/")
    LiveData<ApiResponse<MovieItemResponse>> getMovieItems(@Query("apikey") String apiKey,
                                                           @Query("s") String search);

    @GET("/")
    LiveData<ApiResponse<MovieDetail>> getMovieDetail(@Query("apikey") String apiKey,
                                                      @Query("i") String imdbID);
}
