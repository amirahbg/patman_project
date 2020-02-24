package com.example.test.app.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.app.MainApp;
import com.example.test.app.databinding.FragmentMoviesBinding;
import com.example.test.app.di.AppViewModelFactory;
import com.example.test.app.viewmodel.MoviesViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";
    private FragmentMoviesBinding binding;
    private MoviesViewModel moviesViewModel;

    @Inject
    AppViewModelFactory viewModelFactory;

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

            moviesViewModel.getMovieItems().observe(this, moviesItem -> {
                Log.i(TAG, "onCreateView: " + moviesItem.toString());
            });
        }

        return binding.getRoot();
    }
}
