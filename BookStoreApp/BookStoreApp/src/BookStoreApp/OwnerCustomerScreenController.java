/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import static BookStoreApp.OwnerBookScreenController.admin;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author hoang
 */
public class OwnerCustomerScreenController implements Initializable{
    
    static final Inventory a = new Inventory();
    static final Owner admin = Owner.getInstance("admin","admin",a);    
    
    @FXML
    private TableView<Customer> customerList;
    @FXML
    private TableColumn<Customer, String> usernameList;
    @FXML
    private TableColumn<Customer, String> passwordList;
    @FXML
    private TableColumn<Customer, Integer> pointsList;
    @FXML
    private TextField tempUsername;
    @FXML
    private Button deleteUser;
    @FXML
    private Button addUser;
    @FXML
    private TextField tempPassword;
    @FXML
    private Button back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        usernameList.setCellValueFactory(new PropertyValueFactory<Customer,String>("userName"));
        passwordList.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        pointsList.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("points"));
        
        ObservableList<Customer> value = (FXCollections.observableList(admin.getCustomers()));
        customerList.setItems(value);
    }
    
    @FXML
    void addUserAction(ActionEvent event) {
        admin.addCustomer(tempUsername.getText(),tempPassword.getText());
        usernameList.setCellValueFactory(new PropertyValueFactory<Customer,String>("userName"));
        passwordList.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        pointsList.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("points"));
        
        ObservableList<Customer> value = (FXCollections.observableList(admin.getCustomers()));
        customerList.setItems(value);
    }
    @FXML
    void backAction(ActionEvent event)throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("ownerGUI.fxml"));
            Scene owner = new Scene(root, 700, 400);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(owner);
            window.show();
    }
    @FXML
    void deleteUserAction(ActionEvent event) {
        
        admin.removeCustomer(customerList.getSelectionModel().getSelectedItem());
        customerList.getItems().removeAll(customerList.getSelectionModel().getSelectedItem());
        
        usernameList.setCellValueFactory(new PropertyValueFactory<Customer,String>("userName"));
        passwordList.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        pointsList.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("points"));
        
        ObservableList<Customer> value = (FXCollections.observableList(admin.getCustomers()));
        customerList.setItems(value);

    }
    
}
