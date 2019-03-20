/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectb;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 */
public class AddItemWindowController implements Initializable {
    
    Connection conn = null;
    String dbName = "project";
    String dbUser = "root";
    String dbPass = "root";
    
    @FXML
    private TextField productName;
    @FXML
    private TextField productId;
    @FXML
    private TextField storePrice;
    @FXML
    private TextField deliveryDate;
    @FXML
    private TextField purchaseQuantity;
    @FXML
    private TextField manufacturer;
    @FXML
    private TextField purchasePrice;
    @FXML
    private Button addItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkDbConn();
    }

    public void checkDbConn() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + dbName
                    + "?user=" + dbUser + "&password=" + dbPass);
        } catch (SQLException ex) {
    
        }
    }
    public void addItem() throws SQLException {

        Statement statement = null;
        try{
        statement = conn.createStatement();
            int executeUpdate = statement.executeUpdate("INSERT INTO products value "+"('"+productName.getText()+"' ,"+"'"+productId.getText()+"' ,'"
                    +manufacturer.getText()+"' ,'"+storePrice.getText()+"','"+purchasePrice.getText()+"' ,'"+purchaseQuantity.getText()+"' ,'"+deliveryDate.getText()+"');");
        
            System.out.println(executeUpdate);
            productName.clear();
            productId.clear();
            storePrice.clear();
            deliveryDate.clear();
            purchaseQuantity.clear();
            manufacturer.clear();
            purchasePrice.clear();
            
        }catch(SQLException e){
            
        }
        }
    }


