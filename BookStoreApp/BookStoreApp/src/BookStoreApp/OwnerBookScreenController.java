/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import static BookStoreApp.UserLoginController.admin;
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

//owner book screen
public class OwnerBookScreenController implements Initializable {
    
    static final Inventory a = new Inventory();
    static final Owner admin = Owner.getInstance("admin","admin",a);    
    @FXML
    private TableView<Book> bookList;
    @FXML
    private TableColumn<Book, String> bookNameList;
    @FXML
    private TableColumn<Book, Double> bookPriceList;
    @FXML
    private TextField bookName;
    @FXML
    private TextField bookPrice;
    @FXML
    private Button deleteBook;
    @FXML
    private Button back;
    @FXML
    private Button addBook;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        bookNameList.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        bookPriceList.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        
        ObservableList<Book> value = (FXCollections.observableList(admin.getStock().getBooks()));
        bookList.setItems(value);
    }
    
    @FXML
    void addBookAction(ActionEvent event) {
        admin.addBook(bookName.getText(),Double.parseDouble(bookPrice.getText()));
        bookNameList.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        bookPriceList.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        
        ObservableList<Book> value = (FXCollections.observableList(admin.getStock().getBooks()));
        bookList.setItems(value);
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
    void deleteBookAction(ActionEvent event) {
        admin.removeBook(bookList.getSelectionModel().getSelectedItem());
        bookList.getItems().removeAll(bookList.getSelectionModel().getSelectedItem());
        
        bookNameList.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        bookPriceList.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        
        ObservableList<Book> value = (FXCollections.observableList(admin.getStock().getBooks()));
        bookList.setItems(value);
    }
    
}
