package com.example.test.app.viewmodel;

import com.example.test.app.data.MockMovieItemRepo;
import com.example.test.app.data.model.MovieItem;
import com.example.test.app.data.testutils.LiveDataTestUtil;
import com.example.test.app.utils.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricTestRunner.class)
public class MoviesViewModelTest {

    private MoviesViewModel moviesViewModel;
    private MockMovieItemRepo mockMovieItemRepo;

    @Before
    public void setUp() throws Exception {
        mockMovieItemRepo = new MockMovieItemRepo();
        moviesViewModel = new MoviesViewModel(mockMovieItemRepo);
    }

    @After
    public void tearDown() throws Exception {
        moviesViewModel = null;
        mockMovieItemRepo = null;
    }

    @Test
    public void init_successfulResult() throws InterruptedException {
        // init
        mockMovieItemRepo.setHasError(false);
        moviesViewModel = new MoviesViewModel(mockMovieItemRepo);

        Resource<List<MovieItem>> actual =
                LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        Resource<List<MovieItem>> expected = Resource.success(mockMovieItemRepo.peekData());

        // verify result
        assertThat(actual, is(expected));
    }

    @Test
    public void init_failResult() throws InterruptedException {
        // init
        mockMovieItemRepo.setHasError(true);
        moviesViewModel = new MoviesViewModel(mockMovieItemRepo);

        Resource<List<MovieItem>> actual =
                LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        Resource<List<MovieItem>> expected =
                Resource.error(MockMovieItemRepo.FAKE_ERROR, null);

        // verify result
        assertThat(actual, is(expected));
    }

    @Test
    public void retry_successfulResult() throws InterruptedException {
        mockMovieItemRepo.setHasError(false);
        moviesViewModel.retry();

        Resource<List<MovieItem>> actual =
                LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        Resource<List<MovieItem>> expected = Resource.success(mockMovieItemRepo.peekData());

        // verify result
        assertThat(actual, is(expected));
    }

    @Test
    public void retry_failResult() throws InterruptedException {
        mockMovieItemRepo.setHasError(true);
        moviesViewModel.retry();

        Resource<List<MovieItem>> actual =
                LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        Resource<List<MovieItem>> expected = Resource.error(MockMovieItemRepo.FAKE_ERROR, null);

        // verify result
        assertThat(actual, is(expected));
    }

    @Test
    public void initFail_thenRetry_successfulResult() throws InterruptedException {
        // init
        mockMovieItemRepo.setHasError(true);
        moviesViewModel = new MoviesViewModel(mockMovieItemRepo);

        Resource<List<MovieItem>> actual =
                LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        Resource<List<MovieItem>> expected =
                Resource.error(MockMovieItemRepo.FAKE_ERROR, null);

        // verify result
        assertThat(actual, is(expected));

        mockMovieItemRepo.setHasError(false);
        moviesViewModel.retry();

        actual = LiveDataTestUtil.getOrAwaitValue(moviesViewModel.getMovieItems());

        expected = Resource.success(mockMovieItemRepo.peekData());

        assertThat(actual, is(expected));
    }
}