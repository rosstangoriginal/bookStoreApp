/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;
import java.util.*;
/**
 *
 * @author User
 */
public class Inventory {
    private ArrayList<Book> bookStock;
    
    public Inventory() {
        bookStock = new ArrayList<Book>();
    }
    
    // returns the Inventory's collection of books
    public ArrayList<Book> getBooks() {
        return bookStock;
    }
    
    // displays all the books currently in the Inventory's bookStock
    public void displayBooks() {
        for(Book b:bookStock) {
            b.display();
        }
    }
    
    // removes the first instance of a given book from the Inventory's collection of books
    public void remove(Book b) {
        boolean finished = false;
        for(int i = 0; i<bookStock.size(); i++) {
            if( (!finished)&&(bookStock.get(i).equals(b)) ) {
                bookStock.remove(i);
                finished = true;
            }
        }
    }
    
    // adds a given book to the Inventory's collection of books
    public void add(String name, double price) {
        bookStock.add(new Book(name, price));
    }
}
