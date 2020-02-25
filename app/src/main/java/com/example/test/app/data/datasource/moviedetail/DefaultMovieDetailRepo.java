package com.example.test.app.data.datasource.moviedetail;

import com.example.test.app.data.api.ImdbService;
import com.example.test.app.data.datasource.movieitem.DefaultMovieItemRepo;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.utils.ApiResponse;
import com.example.test.app.utils.AppExecutors;
import com.example.test.app.utils.NetworkBoundResource;
import com.example.test.app.utils.RateLimiter;
import com.example.test.app.utils.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

@Singleton
public class DefaultMovieDetailRepo implements MovieDetailRepo {
    private final MovieDetailDao movieDetailDao;
    private final RateLimiter rateLimiter;
    private final AppExecutors appExecutors;
    private final ImdbService imdbService;

    @Inject
    public DefaultMovieDetailRepo(@NonNull MovieDetailDao movieDetailDao,
                                  @NonNull ImdbService imdbService,
                                  @NonNull RateLimiter rateLimiter,
                                  @NonNull AppExecutors appExecutors) {
        this.movieDetailDao = movieDetailDao;
        this.imdbService = imdbService;
        this.rateLimiter = rateLimiter;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<MovieDetail>> getMovieDetail(String imdbID) {
        return new NetworkBoundResource<MovieDetail, MovieDetail>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull MovieDetail item) {
                movieDetailDao.insertMovieDetails(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable MovieDetail data) {

                return data == null || rateLimiter.shouldFetch(data.getImdbID());
            }

            @NonNull
            @Override
            protected LiveData<MovieDetail> loadFromDb() {
                return movieDetailDao.getMovieDetail(imdbID);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieDetail>> createCall() {
                return imdbService.getMovieDetail("3e974fca", imdbID);
            }
        }.asLiveData();
    }
}
