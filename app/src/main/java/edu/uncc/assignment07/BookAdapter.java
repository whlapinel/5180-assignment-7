package edu.uncc.assignment07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import edu.uncc.assignment07.databinding.BookListItemBinding;
import edu.uncc.assignment07.databinding.GenreListItemBinding;

public class BookAdapter extends ArrayAdapter<Book> {
    private List<Book> books;
    private Context context;

    public BookAdapter(@NonNull Context context, List<Book> books) {
        super(context, 0, books);
        this.books = books;
        this.context = context;
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        BookListItemBinding binding;
        if (listItemView == null) {
            binding = BookListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        } else {
            binding = BookListItemBinding.bind(listItemView);
        }
        listItemView = binding.getRoot();
        Book currentBook = books.get(position);
        binding.bookTitleTextView.setText(currentBook.getTitle());
        binding.authorNameTextView.setText(currentBook.getAuthor());
        binding.genreTextView.setText(currentBook.getGenre());
        binding.yearTextView.setText(String.valueOf(currentBook.getYear()));
        return listItemView;
    }
}
