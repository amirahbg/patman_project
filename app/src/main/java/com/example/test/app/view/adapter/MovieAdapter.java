package com.example.test.app.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.app.data.model.MovieItem;
import com.example.test.app.databinding.MovieItemBinding;
import com.example.test.app.view.interfaces.IOnItemClickListener;
import com.example.test.app.view.interfaces.ISetData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
        implements ISetData<List<MovieItem>> {

    private List<MovieItem> data;
    private IOnItemClickListener<MovieItem> onItemClickListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (data != null)
            holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void setData(List<MovieItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(IOnItemClickListener<MovieItem> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieItemBinding binding;
        private MovieItem item;

        public MovieViewHolder(@NonNull MovieItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(item);
                }
            });
        }

        public void bind(MovieItem movieItem) {
            this.item = movieItem;
            binding.setMovieItem(movieItem);
            binding.executePendingBindings();
        }
    }
}
