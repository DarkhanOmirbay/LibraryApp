package code.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    @NotEmpty(message = "The title of the book should not be empty")
    @Size(min=2,max = 100,message="The title can consist of 2 to 100 characters in length")
    private String title;
    @NotEmpty(message = "The author's name should not be empty")
    @Size(min=2,max = 100,message="The author's name can be 2 to 100 characters long")
    private String author;
    @Min(value = 1500,message = "the year should be more than 1500")
    private int year;
    public Book(){

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
