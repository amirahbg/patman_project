package com.example.test.app.data;

import com.example.test.app.data.datasource.moviedetail.MovieDetailRepo;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.data.testutils.Faker;
import com.example.test.app.utils.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@Singleton
public class MockMovieDetailRepo implements MovieDetailRepo {
    public static final String FAKE_ERROR = "fake error";
    private MutableLiveData<Resource<MovieDetail>> movieDetail;
    private static MovieDetail sMovieDetail;
    private boolean hasError = false;

    @Inject
    public MockMovieDetailRepo() {
        populateData();
    }

    @Override
    public LiveData<Resource<MovieDetail>> getMovieDetail(String imdbID) {
        MutableLiveData<Resource<MovieDetail>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));
        if (hasError) {
            result.setValue(Resource.error(FAKE_ERROR, null));
        } else {
            sMovieDetail.setImdbID(imdbID);
            result.setValue(Resource.success(sMovieDetail));
        }

        return result;
    }

    private void populateData() {
        sMovieDetail = new MovieDetail(Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString(), Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString(), Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString(), Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString(), Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString(), Faker.fakeString(), Faker.fakeString(), Faker.fakeString(),
                Faker.fakeString());
    }

    public MovieDetail peekData() {
        return sMovieDetail;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
