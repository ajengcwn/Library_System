import javax.swing.*;
import java.util.ArrayList;

public class Book
{
    private String title;
    private Integer shelf;
    private int totalBooks;

    public Book(String title, Integer shelf, int totalBooks)
    {
        this.title = title;
        this.shelf = shelf;
        this.totalBooks = totalBooks;
    }

    public String getTitle() {return title;}

    public Integer getShelf()
    {
        return shelf;
    }

    public int getTotalBooks() {return totalBooks;}

    public void setTotalBooks(String symbol)
    {
        if(symbol.equals("-"))
        {
            this.totalBooks--;
        } else if(symbol.equals("+"))
        {
            this.totalBooks++;
        }
    }

    public static void getTitles(Integer shelf, JComboBox titleDropDown, BST_IntKey<Book> books)
    {
        ArrayList<Book> booksOnShelf = books.search(shelf);

        for(Book book : booksOnShelf)
        {
            if(book.totalBooks != 0)
            {
                titleDropDown.addItem(book.getTitle());
            }
        }
    }

    public static Book getBook(Integer shelf, JComboBox dropDown, BST_IntKey<Book> books)
    {
        ArrayList<Book> booksOnShelf = books.search(shelf);

        for (Book book : booksOnShelf)
        {
            if(book.getTitle() == dropDown.getSelectedItem())
            {
                return book;
            }
        }

        return null;
    }
}
