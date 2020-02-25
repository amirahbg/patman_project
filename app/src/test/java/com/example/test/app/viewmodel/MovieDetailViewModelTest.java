package com.example.test.app.viewmodel;

import com.example.test.app.data.MockMovieDetailRepo;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.data.testutils.LiveDataTestUtil;
import com.example.test.app.utils.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MovieDetailViewModelTest {
    private MockMovieDetailRepo mockMovieDetailRepo;
    private MovieDetailViewModel movieDetailViewModel;

    @Before
    public void setUp() throws Exception {
        mockMovieDetailRepo = new MockMovieDetailRepo();
        movieDetailViewModel = new MovieDetailViewModel(mockMovieDetailRepo);
    }

    @After
    public void tearDown() throws Exception {
        mockMovieDetailRepo = null;
        movieDetailViewModel = null;
    }

    @Test
    public void init_noResult() throws InterruptedException {
        mockMovieDetailRepo.setHasError(false);
        movieDetailViewModel = new MovieDetailViewModel(mockMovieDetailRepo);

        try {
            Resource<MovieDetail> actual =
                    LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());
            fail("should have been thrown an error");
        } catch (RuntimeException e) {
            // success
        }
    }

    @Test
    public void setId_successfulResult() throws InterruptedException {
        mockMovieDetailRepo.setHasError(false);
        movieDetailViewModel.setImdbId("someId");

        Resource<MovieDetail> actual =
                LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());

        Resource<MovieDetail> expected = Resource.success(mockMovieDetailRepo.peekData());

        assertThat(actual, is(expected));
    }

    @Test
    public void setId_failResult() throws InterruptedException {
        mockMovieDetailRepo.setHasError(true);
        movieDetailViewModel.setImdbId("someId");

        Resource<MovieDetail> actual =
                LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());

        Resource<MovieDetail> expected = Resource.error(MockMovieDetailRepo.FAKE_ERROR, null);

        assertThat(actual, is(expected));
    }

    @Test
    public void retry_noResult() throws InterruptedException {
        mockMovieDetailRepo.setHasError(false);
        movieDetailViewModel.retry();
        try {

            Resource<MovieDetail> actual =
                    LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());
            fail();
        } catch (RuntimeException e) {
            // success
        }
    }

    @Test
    public void setId_thenRetry_successfulResult() throws InterruptedException {
        mockMovieDetailRepo.setHasError(false);
        movieDetailViewModel.setImdbId("someId");

        Resource<MovieDetail> actual =
                LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());
        Resource<MovieDetail> expected = Resource.success(mockMovieDetailRepo.peekData());

        assertThat(actual, is(expected));

        movieDetailViewModel.retry();

        actual = LiveDataTestUtil.getOrAwaitValue(movieDetailViewModel.getMovieDetail());
        expected = Resource.success(mockMovieDetailRepo.peekData());

        assertThat(actual, is(expected));
    }
}