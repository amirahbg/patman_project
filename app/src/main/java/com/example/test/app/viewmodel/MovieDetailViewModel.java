package com.example.test.app.viewmodel;

import com.example.test.app.data.datasource.moviedetail.MovieDetailRepo;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.utils.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<String> imdbId;
    private LiveData<Resource<MovieDetail>> movieDetail;
    private final MovieDetailRepo movieDetailRepo;

    @Inject
    public MovieDetailViewModel(MovieDetailRepo movieDetailRepo) {
        this.movieDetailRepo = movieDetailRepo;

        imdbId = new MutableLiveData<>();
        movieDetail = Transformations.switchMap(imdbId, this.movieDetailRepo::getMovieDetail);
    }

    public void retry() {
        if (imdbId.getValue() != null) {
            imdbId.setValue(imdbId.getValue());
        }
    }

    public void setImdbId(String imdbId) {
        this.imdbId.setValue(imdbId);
    }

    public LiveData<Resource<MovieDetail>> getMovieDetail() {
        return movieDetail;
    }
}
