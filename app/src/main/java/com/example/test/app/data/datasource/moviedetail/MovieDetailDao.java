package com.example.test.app.data.datasource.moviedetail;

import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.data.model.MovieItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDetailDao {

    @Query("SELECT * FROM MovieDetail WHERE :imdbID = imdbID")
    LiveData<MovieDetail> getMovieDetail(String imdbID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieDetails(MovieDetail movieDetail);
}
