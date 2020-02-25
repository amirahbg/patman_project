package com.example.test.app.data.datasource.moviedetail;

import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.utils.Resource;

import androidx.lifecycle.LiveData;

public interface MovieDetailRepo {
    LiveData<Resource<MovieDetail>> getMovieDetail(String imdbID);
}
