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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uncc.assignment07.databinding.FragmentBooksBinding;
import edu.uncc.assignment07.databinding.FragmentGenresBinding;

public class BooksFragment extends Fragment {
    private static final String TAG = "BooksFragment";
    private static final String ARG_PARAM_GENRE = "ARG_PARAM_GENRE";
    private String mGenre;
    FragmentBooksBinding binding;
    BooksListener mListener;

    ArrayList<Book> mBooks = new ArrayList<>();

    public static BooksFragment newInstance(String genre) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_GENRE, genre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGenre = getArguments().getString(ARG_PARAM_GENRE);
        }
    }


    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        BookAdapter bookAdapter = new BookAdapter(getContext(), mBooks);
        binding.bookListView.setAdapter(bookAdapter);
        binding.buttonBack.setOnClickListener(v -> {
            mListener.closeBooks();
        });
        binding.bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = mBooks.get(position);
                mListener.gotoBookDetails(book);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Books");
        mBooks.clear();
        mBooks.addAll(Data.getBooksByGenre(mGenre));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BooksListener) {
            mListener = (BooksListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement BooksListener");
        }
    }

    interface BooksListener {
        void closeBooks();

        void gotoBookDetails(Book book);
    }
}