package com.example.test.app.data;

import com.example.test.app.data.datasource.moviedetail.MovieDetailDao;
import com.example.test.app.data.datasource.movieitem.MovieItemDao;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.data.model.MovieItem;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieItem.class, MovieDetail.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract MovieItemDao getMovieItemDao();

    public abstract MovieDetailDao getMovieDetailDao();
}
