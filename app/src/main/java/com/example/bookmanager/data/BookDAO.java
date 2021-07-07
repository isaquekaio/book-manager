package com.example.bookmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanager.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private SQLiteDatabase db;
    private static BookDAO instance;

    private BookDAO(Context context){
        DBHelper dbHelprt = DBHelper.getInstance(context);
        db = dbHelprt.getWritableDatabase();
    }

    public static BookDAO getInstance(Context context){
        if (instance == null){
            instance = new BookDAO(context.getApplicationContext());
        }
        return instance;
    }

    public List<Book> list(){
        String[] columns = {
            BookContract.Columns._ID,
            BookContract.Columns.title,
            BookContract.Columns.author,
            BookContract.Columns.publishing_house,
            BookContract.Columns.borrowed,
        };

        List<Book> books = new ArrayList<>();
        try(
            Cursor c = db.query(BookContract.TABLE_NAME, columns, null, null, null, null, BookContract.Columns.title)
        ){
            if(c.moveToFirst()){
                do {
                    Book book = BookDAO.fromCursor(c);
                    books.add(book);
                }while (c.moveToNext());

            }
        }
        return books;
    }

    private static Book fromCursor(Cursor c){
        Long id = c.getLong(c.getColumnIndex(BookContract.Columns._ID));
        String title = c.getString(c.getColumnIndex(BookContract.Columns.title));
        String author = c.getString(c.getColumnIndex(BookContract.Columns.author));
        String publishing_house = c.getString(c.getColumnIndex(BookContract.Columns.publishing_house));
        int borrowed = c.getInt(c.getColumnIndex(BookContract.Columns.borrowed));

        return new Book(id, title, author, publishing_house, borrowed);
    }

    public void save(Book book){
        ContentValues values = new ContentValues();
        values.put(BookContract.Columns.title, book.getTitle());
        values.put(BookContract.Columns.author, book.getAuthor());
        values.put(BookContract.Columns.publishing_house, book.getPublishing_house());
        values.put(BookContract.Columns.borrowed, book.getBorrowed());

        Long id = db.insert(BookContract.TABLE_NAME, null, values);
        book.setId(id);
    }

    public void update(Book book){
        ContentValues values = new ContentValues();
        values.put(BookContract.Columns.title, book.getTitle());
        values.put(BookContract.Columns.author, book.getAuthor());
        values.put(BookContract.Columns.publishing_house, book.getPublishing_house());
        values.put(BookContract.Columns.borrowed, book.getBorrowed());

        db.update(BookContract.TABLE_NAME, values, BookContract.Columns._ID+"?", new String[]{String.valueOf(book.getId())});
    }

    public void delete(Book book){
        db.delete(BookContract.TABLE_NAME, BookContract.Columns._ID+"?", new String[]{String.valueOf(book.getId())});
    }

}