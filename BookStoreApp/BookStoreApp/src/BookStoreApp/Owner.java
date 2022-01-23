/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author User
 */
public class Owner extends Person {
    private static Owner instance;
    private static Inventory stock;
    private static ArrayList<Customer> customers;
    private static Integer token = -1;
    
    private Owner(String userName, String password, Inventory stock) {
        super(userName, password);
        this.stock = stock;
        customers = new ArrayList<Customer>();
    }
    
    // Allows for a single instance of the Owner class
    public static Owner getInstance(String userName, String password, Inventory stock) {
        if(instance==null) {
            instance = new Owner(userName, password, stock);
            
            try {
                File myFile1 = new File("books.txt");
                File myFile2 = new File("customers.txt");
                myFile1.createNewFile();
                myFile2.createNewFile();
            } catch (IOException e) {
                System.out.println("File could not be created");
            }
        }
        return instance;
    }
    
    // returns the list of Customers
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    //returns the admins inventory/stock
    public Inventory getStock(){
        return stock;
    }
    
    // adds a customer to the list of Customers
    public static void addCustomer(String userName, String password) {
        boolean exists = false;
        for(Customer c:customers) {
            if(c.getUserName().equals(userName)) {
                exists = true;
            }
        }
        if(!exists) {
            customers.add(new Customer(userName, password, stock));
        }
    }
    
    public void removeCustomer(Customer c) {
        customers.remove(c);
    }
    
    public static void addBook(String name, double price) {
        boolean exists = false;
        for(Book b:stock.getBooks()) {
            if(b.getName().equals(name)) {
                exists = true;
            }
        }
        if(!exists) {
            stock.add(name, price);
        }
    }
    
    public void removeBook(Book b) {
        stock.remove(b);
    }
    
    public void setToken(int a){
        token = a;
    }
    public int getToken(){
        return token;
    }
    public static void load() {
        try {
            // Read the books.txt file and load those books into the Inventory
            FileReader frBooks = new FileReader("books.txt");
            int i;
            while((i=frBooks.read()) != -1) {
                String s1 = "";
                String s2 = "";
                char ch = (char)i;
                // read the book name
                while(ch != '#') {
                    s1 += ch;
                    ch = (char)frBooks.read();
                }
                // read the book price
                ch = (char)frBooks.read();
                while(ch != '#') {
                    s2 += ch;
                    ch = (char)frBooks.read();
                }
                addBook(s1, Double.valueOf(s2));  
            }
            frBooks.close();
            
            // Read the customers.txt file and load those customers into the ArrayList
            FileReader frCust = new FileReader("customers.txt");
            int counter = 0;
            while((i=frCust.read()) != -1) {
                String s1 = "";
                String s2 = "";
                String s3 = "";
                char ch = (char)i;
                // read the username
                while(ch != '#') {
                    s1 += ch;
                    ch = (char)frCust.read();
                }
                // read the password
                ch = (char)frCust.read();
                while(ch != '#') {
                    s2 += ch;
                    ch = (char)frCust.read();
                }
                // read the points
                ch = (char)frCust.read();
                while(ch != '#') {
                    s3 += ch;
                    ch = (char)frCust.read();
                }   
                addCustomer(s1, s2);
                customers.get(counter).setPoint(Integer.valueOf(s3));
                counter++;
            }
            frCust.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void save() {
        try {
            // saves all the existing books in Invetory to books.txt
            FileWriter fwBooks = new FileWriter("books.txt");
            String bookString = "";
            for(Book b:stock.getBooks()) {
                bookString += b.getName() + "#";
                bookString += b.getPrice() + "#";
            }
            fwBooks.write(bookString);
            fwBooks.flush();
            fwBooks.close();
            
            // saves all the existing customers to customers.txt
            FileWriter fwCust = new FileWriter("customers.txt");
            String custString = "";
            for(Customer c:customers) {
                custString += c.getUserName() + "#";
                custString += c.getPassword() + "#";
                custString += c.getPoints() + "#";
            }
            fwCust.write(custString);
            fwCust.flush();
            fwCust.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }  
}
