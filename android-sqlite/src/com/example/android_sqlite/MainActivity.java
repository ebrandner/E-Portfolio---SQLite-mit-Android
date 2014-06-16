package com.example.android_sqlite;

import java.util.List;
import com.example.android_sqlite.Book;
import com.example.android_sqlite.MySQLiteHelper;
import android.os.Bundle;
import android.app.Activity;
 
public class MainActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        MySQLiteHelper db = new MySQLiteHelper(this);
 
        // Bücher hinzufügen
        	db.addBook(new Book("The Definitive Guide to SQLite", "Mike Owens and Grant Allen"));   
        	db.addBook(new Book("Using SQLite", "Jay A. Kreibich"));       
        	db.addBook(new Book("SQLite 3 - Einstieg in die Datenbankwelt", "Key Droessler"));
        	db.addBook(new Book("Programmieren mit Cobol", "Richie McDowell"));  
        	db.addBook(new Book("The SQL Guide to SQLite", "Rick F. van der Lans"));
        	db.addBook(new Book("An Introduction to SQLite - 2nd Edition", "Naoki Nishizawa"));
 
        // alle Bücher auflisten
        List<Book> list = db.getAllBooks();
 
        // Buch löschen
        db.deleteBook(list.get(0));
        
        // alle Bücher
        db.getAllBooks();
        
        // weitere Bücher löschen
        db.deleteBook(list.get(0));
        db.deleteBook(list.get(1));
        
     // alle Bücher
        db.getAllBooks();
 
    }
 
}