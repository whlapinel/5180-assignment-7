package edu.uncc.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.assignment07.databinding.FragmentGenresBinding;

public class GenresFragment extends Fragment {
    private static final String TAG = "GenresFragment";
    List<String> genres;
    GenresListener mListener;

    public GenresFragment() {
        genres = Data.getAllGenres();
    }

    FragmentGenresBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        binding = FragmentGenresBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ArrayAdapter<String> GenreAdapter = new GenreAdapter(getContext(), genres);
        binding.genreListView.setAdapter(GenreAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Genres");
        binding.genreListView.setOnItemClickListener((parent, view1, position, id) -> {
            String genre = genres.get(position);
            mListener.gotoBooksForGenre(genre);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
        if (context instanceof GenresListener) {
            mListener = (GenresListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GenresListener");
        }
    }

    interface GenresListener {
        void gotoBooksForGenre(String genre);
    }
}