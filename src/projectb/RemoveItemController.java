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
public class RemoveItemController implements Initializable {

    Connection conn = null;
    String dbName = "project";
    String dbUser = "root";
    String dbPass = "root";

    @FXML
    private TextField searchItem;
    
    @FXML
    private Button removeItem;

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

    public void removeItem() throws SQLException {

        Statement statement = null;

        statement = conn.createStatement();
        try{
        int executeUpdate = statement.executeUpdate("SET SQL_SAFE_UPDATES=0;");
        int executeUpdate2 = statement.executeUpdate( "DELETE FROM products WHERE id ='"+searchItem.getText()+"'");
        searchItem.clear();
 
    }
    catch(SQLException e) {
      
        }
    
    }
}
