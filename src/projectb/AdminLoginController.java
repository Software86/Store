/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectb;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 */
public class AdminLoginController implements Initializable {
    
    String dbName = "project";
    String dbUser = "root";
    String dbPass = "project";
    Statement statement = null;
    PreparedStatement pst = null;
    Connection conn = null;
    
    
 
    /**
     * Initializes the controller class.
     */
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
            System.out.println("Incorrect login details.");
        }
     }
}
