package com.example.test.app.data.di;

import android.app.Application;

import com.example.test.app.data.LocalDatabase;
import com.example.test.app.data.MockMovieDetailRepo;
import com.example.test.app.data.MockMovieItemRepo;
import com.example.test.app.data.api.ImdbService;
import com.example.test.app.data.datasource.moviedetail.DefaultMovieDetailRepo;
import com.example.test.app.data.datasource.moviedetail.MovieDetailDao;
import com.example.test.app.data.datasource.moviedetail.MovieDetailRepo;
import com.example.test.app.data.datasource.movieitem.DefaultMovieItemRepo;
import com.example.test.app.data.datasource.movieitem.MovieItemDao;
import com.example.test.app.data.datasource.movieitem.MovieItemRepo;
import com.example.test.app.utils.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    public static MovieItemDao provideMovieDao(LocalDatabase localDatabase) {
        return localDatabase.getMovieItemDao();
    }

    @Provides
    @Singleton
    public static LocalDatabase provideAppLocalDatabase(Application application) {
        return Room.databaseBuilder(application,
                LocalDatabase.class, "localdb").build();
    }

    @Singleton
    @Binds
    public abstract MovieItemRepo bindMovieItemRepo(MockMovieItemRepo mockMovieItemRepo);

    @Singleton
    @Provides
    public static ImdbService provideImdbService() {
        return new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ImdbService.class);
    }

    @Singleton
    @Binds
    public abstract MovieDetailRepo bindMovieDetailRepo(MockMovieDetailRepo movieDetailRepo);

    @Provides
    public static MovieDetailDao provideMovieDetailDao(LocalDatabase localDatabase) {
        return localDatabase.getMovieDetailDao();
    }
}
