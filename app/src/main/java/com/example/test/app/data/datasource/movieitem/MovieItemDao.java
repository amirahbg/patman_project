package com.example.test.app.data.datasource.movieitem;

import com.example.test.app.data.model.MovieItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieItemDao {
    @Query("SELECT * FROM MovieItem")
    LiveData<List<MovieItem>> getMovieItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieItem> movieItems);
}
