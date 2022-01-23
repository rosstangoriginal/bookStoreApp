/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author hoang
 */
public class CustomerCheckoutScreenController implements Initializable {
    
    static final Inventory a = new Inventory();
    static final Owner admin = Owner.getInstance("admin","admin",a);
    Customer temp = admin.getCustomers().get(admin.getToken()); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        totalCost.setText(temp.totalCost);
        statusAndPoints.setText(temp.pointStatus);
    }
    
    @FXML
    private Text totalCost;
    @FXML
    private Text statusAndPoints;
    @FXML
    private Button logout;

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
