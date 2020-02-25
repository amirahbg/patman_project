package com.example.test.app.view.fragment;

import com.example.test.app.R;
import com.example.test.app.viewmodel.MoviesViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class MoviesFragmentTest {
    private FragmentScenario<MoviesFragment> scenario;
    private NavController navController;

    @Before
    public void setUp() throws Exception {
        scenario = FragmentScenario.launchInContainer(MoviesFragment.class);
        navController = mock(NavController.class);
    }

    @After
    public void tearDown() throws Exception {
        scenario = null;
        navController = null;
    }

    @Test
    public void scrollRecyclerView() {
        onView(withId(R.id.rvMovies))
                .perform(actionOnItemAtPosition(20, scrollTo()));

        onView(withId(R.id.rvMovies))
                .perform(actionOnItemAtPosition(0, scrollTo()));

        onView(withId(R.id.rvMovies))
                .perform(actionOnItemAtPosition(29, scrollTo()));
    }

    @Test
    public void clickOnItem_navigateToItemSuccessfully() {
        scenario.onFragment(fragment -> {
            Navigation.setViewNavController(fragment.requireView(), navController);
        });

        onView(withId(R.id.rvMovies))
                .perform(actionOnItemAtPosition(17, click()));

        MoviesFragmentDirections.ActionMoviesFragmentToMovieDetailFragment action =
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment("27");
        verify(navController).navigate(action);
    }
}