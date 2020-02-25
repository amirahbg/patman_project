package com.example.test.app.data;

import com.example.test.app.data.datasource.movieitem.MovieItemRepo;
import com.example.test.app.data.model.MovieItem;
import com.example.test.app.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@Singleton
public class MockMovieItemRepo implements MovieItemRepo {
    public static final String FAKE_ERROR = "fake error";
    private MutableLiveData<Resource<List<MovieItem>>> movieItems;
    private static List<MovieItem> sMovieItems;
    private boolean hasError = false;

    @Inject
    public MockMovieItemRepo() {
        populateData();
    }

    private void populateData() {
        sMovieItems = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            sMovieItems.add(new MovieItem("title " + i,
                    "year " + i, String.valueOf(i + 10), "movie",
                    "poster " + i));
        }
    }

    @Override
    public LiveData<Resource<List<MovieItem>>> getMovieItems() {
        MutableLiveData<Resource<List<MovieItem>>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));
        if (hasError) {
            result.setValue(Resource.error(FAKE_ERROR, null));
        } else {
            result.setValue(Resource.success(sMovieItems));
        }

        return result;
    }

    public List<MovieItem> peekData() {
        return sMovieItems;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
