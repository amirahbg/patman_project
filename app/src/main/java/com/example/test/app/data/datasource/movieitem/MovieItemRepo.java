package com.example.test.app.data.datasource.movieitem;

import com.example.test.app.data.model.MovieItem;
import com.example.test.app.utils.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * an interface to access MovieItemRepository
 * concrete implementation of this interface is {@code DefaultMovieItemRepo}
 */
public interface MovieItemRepo {
    LiveData<Resource<List<MovieItem>>> getMovieItems();
}
