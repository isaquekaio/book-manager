package com.example.bookmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanager.R;
import com.example.bookmanager.domain.Book;

import java.util.*;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{

    private List<Book> books;
    private Context context;
    private OnBookListener onBookListener;

    public BookAdapter(List<Book> books, Context context, OnBookListener onBookListener){
        this.books = books;
        this.context = context;
        this.onBookListener = onBookListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        BookHolder bookHolder = new BookHolder(view, onBookListener);

        return bookHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull BookAdapter.BookHolder holder, int position) {
        Book book = books.get(position);
        holder.txtTitle.setText(book.getTitle());
        holder.txtAuthor.setText(book.getAuthor());
        holder.txtPublishingHouse.setText(book.getPublishing_house());

        if(book.getBorrowed() == 1){
            holder.ic_book.setColorFilter(Color.GRAY);
            holder.txtTitle.setTextColor(Color.GRAY);
            holder.ic_star.setVisibility(View.VISIBLE);
        }else{
            holder.ic_book.setColorFilter(Color.parseColor("#0455BF"));
            holder.ic_star.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setItms(List<Book> books){
        this.books = books;
    }

    public Book getItem(int position){
        return books.get(position);
    }

    public class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView txtTitle;
        public TextView txtAuthor;
        public TextView txtPublishingHouse;
        public ImageView ic_book;
        public ImageView ic_star;
        public OnBookListener onBookListener;

        public BookHolder(View view, OnBookListener onBookListener){
            super(view);

            txtTitle = view.findViewById(R.id.txtTitle);
            txtAuthor = view.findViewById(R.id.txtAuthor);
            txtPublishingHouse = view.findViewById(R.id.txtPublishingHouse);
            ic_book = view.findViewById(R.id.ic_book);
            ic_star = view.findViewById(R.id.ic_star);

            this.onBookListener = onBookListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBookListener.onBookClick(getAdapterPosition());
            /*int position = getAdapterPosition();
            Toast.makeText(context, "OnClick "+(position+1), Toast.LENGTH_SHORT).show();*/
        }

        @Override
        public boolean onLongClick(View v) {
            onBookListener.onBookLongClick(getAdapterPosition());
            /*int position = getAdapterPosition();
            Toast.makeText(context, "OnLongClick "+(position+1), Toast.LENGTH_SHORT).show();*/
            return true;
        }
    }

    public interface OnBookListener{
        void onBookClick(int position);
        void onBookLongClick(int position);
    }
}
