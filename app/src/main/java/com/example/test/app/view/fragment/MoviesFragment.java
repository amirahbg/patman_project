package com.example.test.app.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.app.MainApp;
import com.example.test.app.R;
import com.example.test.app.data.model.MovieItem;
import com.example.test.app.databinding.FragmentMoviesBinding;
import com.example.test.app.di.AppViewModelFactory;
import com.example.test.app.utils.Resource;
import com.example.test.app.view.adapter.MovieAdapter;
import com.example.test.app.view.decorator.GridLayoutDecorator;
import com.example.test.app.viewmodel.MoviesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";
    private FragmentMoviesBinding binding;
    private MoviesViewModel moviesViewModel;

    @Inject
    AppViewModelFactory viewModelFactory;
    private MovieAdapter movieAdapter;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        ((MainApp) (context.getApplicationContext())).getAppComponent().inject(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MoviesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentMoviesBinding.inflate(inflater, container, false);

            setupRecyclerView();

            moviesViewModel.getMovieItems().observe(this, moviesItem -> {
                switch (moviesItem.status) {
                    case LOADING:
                        handleLoading(moviesItem);
                        break;

                    case SUCCESS:
                        handleSuccess(moviesItem);
                        break;

                    case ERROR:
                        handleError(moviesItem);
                }
            });

            binding.customLoading.setOnRetryClicked(v -> moviesViewModel.retry());
        }

        return binding.getRoot();
    }

    private void handleError(Resource<List<MovieItem>> data) {
        binding.customLoading.showRetry();

        if (data.message == null)
            Snackbar.make(binding.clContainer, R.string.unknown_error, Snackbar.LENGTH_LONG).show();
        else
            Snackbar.make(binding.clContainer, data.message, Snackbar.LENGTH_LONG).show();

        setData(data.data);
    }

    private void handleSuccess(Resource<List<MovieItem>> data) {
        binding.customLoading.hide();
        setData(data.data);
    }

    private void handleLoading(Resource<List<MovieItem>> data) {
        binding.customLoading.showLoading();
        setData(data.data);
    }

    private void setData(List<MovieItem> data) {
        if (data != null) {
            movieAdapter.setData(data);
        }
    }

    private void setupRecyclerView() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter();
            movieAdapter.setOnItemClickListener(item -> {

                MoviesFragmentDirections.ActionMoviesFragmentToMovieDetailFragment action =
                        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(item.getImdbId());
                Navigation.findNavController(binding.clContainer).navigate(action);
            });
        }

        binding.rvMovies.setAdapter(movieAdapter);
        binding.rvMovies.addItemDecoration(new GridLayoutDecorator(
                getResources().getDimension(R.dimen.grid_item_spacing)));
        binding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}
