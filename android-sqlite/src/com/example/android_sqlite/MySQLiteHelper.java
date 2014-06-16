package com.example.android_sqlite;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android_sqlite.Book;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Datenbank Version
    private static final int DATABASE_VERSION = 1;
    
    // Datenbank Name
    private static final String DATABASE_NAME = "BookDB";
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        // Erstellung eines Strings mit SQL Befehlen
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY, " + 
                "title TEXT, "+
                "author TEXT )";
 
        // hier wird die Datenbank erstellt
        db.execSQL(CREATE_BOOK_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	
        // falls es bereits eine Datenbank gibt wird diese gelöscht
        db.execSQL("DROP TABLE IF EXISTS books");
 
        // Datenbank wird neu erstellt
        this.onCreate(db);
    }
    //---------------------------------------------------------------------
 
    /**
     * CRUD operations (create "add", read "get", update, delete) book 
     * + get all books + delete all books
     */
 
    // Books table name
    private static final String TABLE_BOOKS = "books";
 
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
 
    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR};
 
    public void addBook(Book book){
    	
        Log.d("addBook", book.toString());
        
        // 1. Zeiger auf die Datenbank wird erstellt
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. ContentValues werden erstellt um die Schlüssel und Werte hinzuzufügen
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get Titel 
        values.put(KEY_AUTHOR, book.getAuthor()); // get Autor
 
        // 3. Einfügen
        db.insert(TABLE_BOOKS, // Datenbank
                null, //nullColumnHack
                values); // Schlüssel + Werte
 
        // 4. Schließen
        db.close(); 
    }
 
    public Book getBook(int id){
 
        // 1. Zeiger auf die Datenbank wird erstellt (lesen)
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. Abfrage
        Cursor cursor = 
                db.query(TABLE_BOOKS, // Datenbank
                COLUMNS, // Spaltenname
                " id = ?", // Auswahl
                new String[] { String.valueOf(id) },
                null, // group by
                null, // having
                null, // order by
                null); // limit
 
        // 3. falls Ergebnis, an die erste Stelle
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. Erstellung Objekt Buch
        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
 
        Log.d("getBook("+id+")", book.toString());
 
        // 5. Rückgabe Buch
        return book;
    }
 
    // Alle Bücher
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();
 
        // 1. Abfrage
        String query = "SELECT  * FROM " + TABLE_BOOKS;
 
        // 2. Zeiger auf die Datenbank wird erstellt
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. Schleife über alle Reihen, jeweils Objekterstellung und hinzufügen
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
 
                books.add(book);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllBooks()", books.toString());
 
        // Rückgabe der Liste
        return books;
    }
 
     // Update eines einzelnen Buches
    public int updateBook(Book book) {
 
        // 1. Zeiger auf die Datenbank wird erstellt
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. ContentValues werden erstellt um die Schlüssel und Werte hinzuzufügen
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); // get title 
        values.put("author", book.getAuthor()); // get author
 
        // 3. Update des Datenbankeintrages
        int i = db.update(TABLE_BOOKS, //Datenbank
                values, // Spalte,Wert
                KEY_ID+" = ?", // Auswahl
                new String[] { String.valueOf(book.getId()) }); 
 
        // 4. Schließen
        db.close();
 
        return i;
 
    }
 
    // Löschen eines Buches
    public void deleteBook(Book book) {
 
        // 1. Zeiger auf die Datenbank wird erstellt
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. Löschen
        db.delete(TABLE_BOOKS, //Datenbank
                KEY_ID+" = ?", //Auswahl
                new String[] { String.valueOf(book.getId()) });
 
        // 3. Schließen
        db.close();
 
        Log.d("deleteBook", book.toString());
 
    }

}
