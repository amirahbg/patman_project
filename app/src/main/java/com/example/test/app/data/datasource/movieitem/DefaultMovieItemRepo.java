package com.example.test.app.data.datasource.movieitem;

import com.example.test.app.data.api.ImdbService;
import com.example.test.app.data.model.MovieItem;
import com.example.test.app.data.model.MovieItemResponse;
import com.example.test.app.utils.ApiResponse;
import com.example.test.app.utils.AppExecutors;
import com.example.test.app.utils.NetworkBoundResource;
import com.example.test.app.utils.RateLimiter;
import com.example.test.app.utils.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Response;

@Singleton
public class DefaultMovieItemRepo implements MovieItemRepo {
    public static final String MOVIE_ITEM_KEY = "movie_item_key";
    private final MovieItemDao movieItemDao;
    private final ImdbService imdbService;
    private final AppExecutors appExecutors;
    private final RateLimiter rateLimiter;

    @Inject
    public DefaultMovieItemRepo(@NonNull MovieItemDao movieItemDao,
                                @NonNull ImdbService imdbService,
                                @NonNull AppExecutors appExecutors,
                                @NonNull RateLimiter rateLimiter) {
        this.movieItemDao = movieItemDao;
        this.imdbService = imdbService;
        this.appExecutors = appExecutors;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public LiveData<Resource<List<MovieItem>>> getMovieItems() {
        return new NetworkBoundResource<List<MovieItem>, MovieItemResponse>(appExecutors) {


            @Override
            protected void saveCallResult(@NonNull MovieItemResponse item) {
                movieItemDao.insertMovies(item.getMovieItems());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieItem> data) {
                return rateLimiter.shouldFetch(MOVIE_ITEM_KEY);
            }

            @NonNull
            @Override
            protected LiveData<List<MovieItem>> loadFromDb() {
                return movieItemDao.getMovieItems();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieItemResponse>> createCall() {
                return imdbService.getMovieItems("3e974fca", "batman");
            }

        }.asLiveData();
    }
}
