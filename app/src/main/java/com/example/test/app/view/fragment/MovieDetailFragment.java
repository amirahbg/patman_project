package com.example.test.app.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.app.MainApp;
import com.example.test.app.R;
import com.example.test.app.data.model.MovieDetail;
import com.example.test.app.databinding.FragmentMovieDetailBinding;
import com.example.test.app.di.AppViewModelFactory;
import com.example.test.app.utils.Resource;
import com.example.test.app.viewmodel.MovieDetailViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;


public class MovieDetailFragment extends Fragment {
    private FragmentMovieDetailBinding binding;
    private MovieDetailViewModel movieDetailViewModel;

    @Inject
    AppViewModelFactory viewModelFactory;

    public MovieDetailFragment() {
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

        movieDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MovieDetailViewModel.class);

        if (getArguments() != null) {
            String imdbId = MovieDetailFragmentArgs.fromBundle(getArguments()).getImdbId();
            movieDetailViewModel.setImdbId(imdbId);
        } else {
            throw new RuntimeException("Argument should not be null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (binding == null) {
            binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
            movieDetailViewModel.getMovieDetail().observe(this, movieDetailResource -> {
                switch (movieDetailResource.status) {
                    case ERROR:
                        handleError(movieDetailResource);
                        break;

                    case SUCCESS:
                        handleSuccess(movieDetailResource);
                        break;

                    case LOADING:
                        handleLoading(movieDetailResource);
                        break;
                }
            });
        }
        return binding.getRoot();
    }

    private void handleLoading(Resource<MovieDetail> movieDetailResource) {
        binding.customLoading.showLoading();

        setData(movieDetailResource.data);
    }

    private void handleSuccess(Resource<MovieDetail> movieDetailResource) {
        binding.customLoading.hide();

        setData(movieDetailResource.data);
    }

    private void handleError(Resource<MovieDetail> movieDetailResource) {
        binding.customLoading.showRetry();
        if (movieDetailResource.message != null) {
            Snackbar.make(binding.clContainer, movieDetailResource.message, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(binding.clContainer, R.string.unknown_error, Snackbar.LENGTH_LONG).show();
        }

        setData(movieDetailResource.data);
    }

    private void setData(MovieDetail data) {
        if (data != null) {
            binding.setMovieDetail(data);
        }
    }
}
