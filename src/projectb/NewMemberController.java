/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class NewMemberController implements Initializable {

    Connection conn = null;
    String dbName = "project";
    String dbUser = "root";
    String dbPass = "root";
    
        @FXML
    private TextField firstNameTF;
            
    @FXML
    private TextField lastNameTF;
    
    @FXML
    private TextField personalNumberTF;
    
    @FXML
    private TextField adressTF;
    
    @FXML
    private TextField phoneNumberTF;
    
    @FXML
    private TextField genderTF;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void addNewMember() throws IOException {
        Statement statement = null;
        try {
           statement = conn.createStatement();
           int executeUpdate = statement.executeUpdate("INSERT INTO customers VALUES('" + firstNameTF.getText() + "', '" + lastNameTF.getText() + "', '" + personalNumberTF.getText() + "', '" + adressTF.getText() + "', '" + phoneNumberTF.getText() + "', '" + genderTF.getText() + "', '0');" ); 
           Stage stage = (Stage) lastNameTF.getScene().getWindow();
           stage.close();
        } catch (SQLException e) {
                
        } 
    }
    
}
