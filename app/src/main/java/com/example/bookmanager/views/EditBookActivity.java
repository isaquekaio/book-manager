package com.example.bookmanager.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.R;
import com.example.bookmanager.data.BookDAO;
import com.example.bookmanager.domain.Book;

public class EditBookActivity extends AppCompatActivity {

    private EditText edt_title;
    private EditText edt_author;
    private EditText edt_publishing_house;
    private CheckBox check_borrowed;

    private BookDAO bookDAO;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        edt_title = findViewById(R.id.edt_title);
        edt_author = findViewById(R.id.edt_author);
        edt_publishing_house = findViewById(R.id.edt_publishing_house);
        check_borrowed = findViewById(R.id.check_borrowed);

        bookDAO = BookDAO.getInstance(this);
        book = (Book) getIntent().getSerializableExtra("book");

        if(book != null){
            edt_title.setText(book.getTitle());
            edt_author.setText(book.getAuthor());
            edt_publishing_house.setText(book.getPublishing_house());
            check_borrowed.setChecked((book.getBorrowed() == 1) ? true : false);
        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void process(View view) {
        String title = edt_title.getText().toString();
        String author = edt_author.getText().toString();
        String publishing_house = edt_publishing_house.getText().toString();
        int borrowed = check_borrowed.isChecked() ? 1 : 0;

        String msg;
        if (book == null){
            Book book = new Book(title, author, publishing_house, borrowed);
            bookDAO.save(book);
            msg = "Book add with sucess! ID="+book.getId();

        }else {
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublishing_house(publishing_house);
            book.setBorrowed(borrowed);

            bookDAO.update(book);
            msg = "Book update with sucess! ID="+book.getId();
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        setResult(RESULT_OK);
        finish();
    }
}