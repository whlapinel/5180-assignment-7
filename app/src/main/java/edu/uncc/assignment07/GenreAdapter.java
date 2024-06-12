package edu.uncc.assignment07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.uncc.assignment07.databinding.FragmentGenresBinding;
import edu.uncc.assignment07.databinding.GenreListItemBinding;

public class GenreAdapter extends ArrayAdapter<String> {
    private List<String> genres;
    private Context context;

    public GenreAdapter(@NonNull Context context, List<String> genres) {
        super(context, 0, genres);
        this.genres = genres;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        GenreListItemBinding binding;
        if (listItemView == null) {
            binding = GenreListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        } else {
            binding = GenreListItemBinding.bind(listItemView);
        }
        listItemView = binding.getRoot();
        String currentGenre = genres.get(position);
        binding.textViewGenreItem.setText(currentGenre);

        return listItemView;
    }
}
