/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hoang
 */

public class UserLoginController{
    static final Inventory a = new Inventory();
    static final Owner admin = Owner.getInstance("admin","admin",a);
    //static customer 
    /////////////////login Controller///////////////////////////

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button help;
    @FXML
    private Button login;
    @FXML
    private Text actiontarget;
    
    @FXML
    private void helpAction(ActionEvent event) {
        actiontarget.setText("If you cannot login, please contact the administrator.");
    }
    public boolean confirmCustomer(){
        boolean a = false;
        if(admin.getCustomers().size() != 0){
            for(int i =0; i <admin.getCustomers().size();i++){
                if((admin.getCustomers().get(i).getUserName().equals(userName.getText()))){
                    if((admin.getCustomers().get(i).getPassword().equals(password.getText()))){
                        admin.setToken(i);
                        a = true;
                        break;
                    }
                }
            }
        }
        return a;
    }
    
    @FXML
    private void loginAction(ActionEvent event) throws InterruptedException, Exception{
        if((userName.getText().equals(admin.userName)) && password.getText().equals(admin.password)){
            //Owner GUI
            Parent root = FXMLLoader.load(getClass().getResource("ownerGUI.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("ownerGUI");
            window.setScene(owner);
            window.show();
        }
        if(confirmCustomer()){
            //Customer GUI
            Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
            Scene customer = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("customerGUI");
            window.setScene(customer);
            window.show();
        }
        else{
            actiontarget.setText("Wrong username or password. Try Again.");
        }
    }
    
    /////////////////ownerGUI controller////////////////////////
    @FXML
    private Button books;
    @FXML
    private Button customers;
    @FXML
    private Button logOut;

    @FXML
    void booksAction(ActionEvent event)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ownerBookScreen.fxml"));
        Scene owner = new Scene(root, 700, 400);
       
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("ownerGUI-bookInventory");
        window.setScene(owner);
        window.show();
    }
    @FXML
    void customersAction(ActionEvent event)throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("ownerCustomerScreen.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("ownerGUI-customerRegistry");
            window.setScene(owner);
            window.show();
    }
    @FXML
    void logOutAction(ActionEvent event) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("userLogin");
            window.setScene(owner);
            window.show();
    }
    
    ///////////////////owner book screen////////////////////////////////////////////////////

    ////////////////////////////owner customer screen///////////////////////////////////////

    ///////////////////////customerGUI controller///////////////////////////////////////////
}
