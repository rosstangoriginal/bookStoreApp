/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

/**
 *
 * @author User
 */
public abstract class Person {
    protected String userName;
    protected String password;
    
    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    protected void viewBooks() {
        
    }
    
    protected void logIn() {
        
    }
    
    protected void logOut() {
        
    }
}
