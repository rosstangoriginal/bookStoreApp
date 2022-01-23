/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStoreApp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class userLogin extends Application{
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene scene = new Scene(root, 700, 400);
        primaryStage.setTitle("userLogin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
         
    @Override
    public void stop() {
        Owner.save();
    }
    
    public static void main(String [] args){
        Inventory stock = new Inventory();
        Owner.getInstance("admin", "admin", stock);
        Owner.load();
               
        launch(args);
    }
}
