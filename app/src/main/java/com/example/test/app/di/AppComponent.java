package com.example.test.app.di;

import com.example.test.app.data.di.AppModule;
import com.example.test.app.data.di.ViewModelModule;
import com.example.test.app.view.fragment.MovieDetailFragment;
import com.example.test.app.view.fragment.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AppModule.class,
        ViewModelModule.class,
        AndroidModule.class
})
public interface AppComponent {
    void inject(MoviesFragment moviesFragment);

    void inject(MovieDetailFragment movieDetailFragment);
}
