package com.example.test.app.di;

import com.example.test.app.viewmodel.MovieDetailViewModel;
import com.example.test.app.viewmodel.MoviesViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel.class)
    abstract ViewModel bindMoviesViewModel(MoviesViewModel moviesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    abstract ViewModel bindMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}