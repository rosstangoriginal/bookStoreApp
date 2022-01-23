/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;
import java.util.*;
import java.lang.Math.*;
/**
 *
 * @author User
 */
public class Customer extends Person {
    private int points = 0;
    private String status;
    private ArrayList<Book> cart;
    private Inventory stock;
    String totalCost, pointStatus;
            
    public Customer(String userName, String password, Inventory stock) {
        super(userName, password);
        this.stock = stock;
        status = "silver";
        cart = new ArrayList<Book>();
    }
    
    // returns the Customer's number of points
    public int getPoints() {
        return points;
    }
    
    public void setPoint(int points) {
        this.points = points; 
    }
    
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    // checks and updates the Customer's current status and returns it
    public String getStatus() {
        if(points<1000) {
            status = "silver";
        } else {
            status = "gold";
        }
        return status;
    }
    
    // returns the collection of books in the cart MAY REMOVE IF NOT CALLED FROM OUTSIDE CLASS
    public ArrayList<Book> getCart() {
        return cart;
    }
    public void addBook(Book a){
        cart.add(a);
    }
    // generates a welcome String based on the Customer's current points and status
    public String welcome() {
        return "Welcome " + this.userName + ". You have " + points + " points. Your status is " + this.getStatus();
    }
    
    // buys books that are currently in the Customer's cart
    // Message at top displaying the Total Cost
    // Awards the Customer 10 points for every CAD spent
    // Message in the middle on the Customer's updated points and status
    // The bought books are removed from the Inventory and the cart is emptied
    public void buyBook(double discount) {
        // print statements will likely be modified with GUI
        totalCost = ("Total Cost: " + this.calcTC(discount));
        points += (int)(this.calcTC(discount)*10);
        pointStatus = ("Points: " + this.getPoints() + ", Status: " + this.getStatus() + ".");
        for(Book b:cart) {
            stock.remove(b);
        }
        cart.clear();
    }

    // calculate the total cost of books in the cart applying the discount up to the cost of books
    private double calcTC(double discount) {
        double total = 0;
        for(Book b:cart) {
            total += b.getPrice();
        }
        total -= Math.min(discount, total);
        return total;
    }
    
    // redeems any available points and then buys the selected books
    // Every 100 points is a potential 1 CAD discount
    public void redeemBuyBook() {
        double redeemableCAD = this.points/100.00;
        int tempPoints = points;
        double total = this.calcTC(0);
        if((total - redeemableCAD)>=0){
            points = 0;
            total -= redeemableCAD;
        }
        if((total - redeemableCAD) < 0){
            points = (int)redeemableCAD - (int)total;
            total = 0;
        }
        // WILL BE CHANGED WITH GUI 
        totalCost = ("Total Cost: " + total);
        pointStatus = ("Points: " + (this.getPoints()+total) + ", Status: " + this.getStatus() + "." + "\nPoints Redeemed: " + (tempPoints-points));
        points += total;
        for(Book b:cart) {
            stock.remove(b);
        }
        cart.clear();
    }
}
