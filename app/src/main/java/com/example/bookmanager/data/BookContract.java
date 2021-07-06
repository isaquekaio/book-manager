package com.example.bookmanager.data;

public class BookContract {
    public static String TABLE_NAME = "book";

    public static final class Columns{
        public static final String _ID = "_id";
        public static final String title = "title";
        public static final String author = "author";
        public static final String publishing_house = "publishing_house";
        public static final String borrowed = "borrowed";
    }
}
