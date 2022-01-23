/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import static BookStoreApp.OwnerBookScreenController.admin;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author hoang
 */
public class CustomerGUIController implements Initializable {
    
    static final Inventory a = new Inventory();
    static final Owner admin = Owner.getInstance("admin","admin",a);
    Customer temp = admin.getCustomers().get(admin.getToken());    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        welcomeMessage.setText(temp.welcome());
        bookName.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        selection.setCellValueFactory(new PropertyValueFactory<Book,CheckBox>("check"));
        
        ObservableList<Book> value = (FXCollections.observableList(admin.getStock().getBooks()));
        bookList.setItems(value);
    }
    @FXML
    private TableView<Book> bookList;
    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, Double> bookPrice;
    @FXML
    private TableColumn<Book, CheckBox> selection;
    
    @FXML
    private Button buy;
    @FXML
    private Button buyAndRedeem;
    @FXML
    private Button logout;
    @FXML
    private Text welcomeMessage;
    
    public void checkout(){
        for(int i = 0;i < admin.getStock().getBooks().size();i++){
            if((admin.getStock().getBooks().get(i).getCheck()).isSelected() == true){
                Book tempBook = admin.getStock().getBooks().get(i);
                admin.removeBook(tempBook);
                (admin.getCustomers().get(admin.getToken())).addBook(tempBook);
                checkout();
            }
        }
    }
    @FXML
    void buyAction(ActionEvent event) throws IOException, Exception {
            //book calculatiosn go here
            checkout();
            (admin.getCustomers().get(admin.getToken())).buyBook(0);
            Parent root = FXMLLoader.load(getClass().getResource("customerCheckoutScreen.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("userLogin");
            window.setScene(owner);
            window.show();
    }
    @FXML
    void buyAndRedeemAction(ActionEvent event) throws IOException, Exception {
            //book calculations go here
            checkout();
            (admin.getCustomers().get(admin.getToken())).redeemBuyBook();
            Parent root = FXMLLoader.load(getClass().getResource("customerCheckoutScreen.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("userLogin");
            window.setScene(owner);
            window.show();
    }
    @FXML
    void logoutAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
            Scene owner = new Scene(root, 700, 400);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("userLogin");
            window.setScene(owner);
            window.show();
    }

}
