package com.example.test.app.data;

import com.example.test.app.data.datasource.movieitem.MovieItemRepo;
import com.example.test.app.viewmodel.MoviesViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@Singleton
public class FakeMoviesViewModel extends MoviesViewModel {
    @Inject
    public FakeMoviesViewModel(@NonNull MovieItemRepo movieItemRepo) {
        super(movieItemRepo);
    }
}
