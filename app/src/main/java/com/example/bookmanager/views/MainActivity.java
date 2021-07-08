package com.example.bookmanager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanager.R;
import com.example.bookmanager.adapter.BookAdapter;
import com.example.bookmanager.data.BookDAO;
import com.example.bookmanager.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookDAO bookDAO;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        listBooks.add(new Book(1L,"Android para Leigos","Michael Burton","Alta books",0));
        listBooks.add(new Book(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
        listBooks.add(new Book(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
        listBooks.add(new Book(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
        listBooks.add(new Book(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
        listBooks.add(new Book(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
        listBooks.add(new Book(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));
        */

        bookDAO = BookDAO.getInstance(this);
        List<Book> listBooks = bookDAO.list();

        bookAdapter = new BookAdapter(listBooks, this);

        recyclerView.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getApplicationContext(), EditBookActivity.class);
                startActivityForResult(intent, 100);
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            updateListBook();
        }
    }

    public void updateListBook(){
        List<Book> books = bookDAO.list();
        bookAdapter.setItms(books);
        bookAdapter.notifyDataSetChanged();
    }
}