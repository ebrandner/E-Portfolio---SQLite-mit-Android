package com.example.android_sqlite;

public class Book {

	private int id;
    private String title;
    private String author;
 
    public Book(){}
 
    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }
    
    public Book(int id, String title, String author){
    	super();
    	this.id = id;
    	this.title = title;
    	this.author = author;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }

	public String getTitle() 
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
