package com.example.bookmanager.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.BookAdapter;
import com.example.bookmanager.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Book> listBooks = new ArrayList<>();
        listBooks.add(new Book(1L,"Android para Leigos","Michael Burton","Alta books",0));
        listBooks.add(new Book(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
        listBooks.add(new Book(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
        listBooks.add(new Book(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
        listBooks.add(new Book(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
        listBooks.add(new Book(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
        listBooks.add(new Book(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));

        BookAdapter bookAdapter = new BookAdapter(listBooks, this);

        recyclerView.setAdapter(bookAdapter);
    }
}