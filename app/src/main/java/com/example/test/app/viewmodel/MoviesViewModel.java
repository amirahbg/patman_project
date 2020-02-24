package com.example.test.app.viewmodel;

import com.example.test.app.data.datasource.movieitem.MovieItemRepo;
import com.example.test.app.data.model.MovieItem;
import com.example.test.app.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<Boolean> shouldRetry;
    private LiveData<Resource<List<MovieItem>>> movieItems;
    private MovieItemRepo movieItemRepo;

    @Inject
    public MoviesViewModel(@NonNull MovieItemRepo movieItemRepo) {
        this.movieItemRepo = movieItemRepo;

        shouldRetry = new MutableLiveData<>();
        retry();

        movieItems = Transformations.switchMap(shouldRetry, ignored ->
                this.movieItemRepo.getMovieItems());
    }

    public void retry() {
        shouldRetry.setValue(true);
    }

    public LiveData<Resource<List<MovieItem>>> getMovieItems() {
        return movieItems;
    }
}
