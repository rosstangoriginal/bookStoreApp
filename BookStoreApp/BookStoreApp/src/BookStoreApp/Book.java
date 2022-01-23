/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;
import javafx.scene.control.CheckBox;
/**
 *
 * @author User
 */
public class Book {
    private String name;
    private double price;
    private CheckBox check;
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.check = new CheckBox();
    }
    
    // returns the name of the Book
    public String getName() {
        return name;
    }
    
    // returns the price of the Book
    public double getPrice() {
        return price;
    }
    
    public CheckBox getCheck(){
        return check;
    }
    // WILL BE CODED WITH GUI to display name and price of book
    public void display() {
        
    }
}
