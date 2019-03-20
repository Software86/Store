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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 */
public class LoginController implements Initializable {

    private String passCheck;
    private String userCheck;
    boolean correctPass;
    Connection conn = null;
    String dbName = "newproject";
    String dbUser = "root";
    String dbPass = "project";

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label label;

    @FXML
    private Stage m;

    @FXML
    private Label wrongPass;
    
    @FXML
    private Button loginButton;

    //@FXML
    /*private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/

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

    public void loginCheck() throws IOException {

        Statement statement = null;
        ResultSet rsUser = null;

        try {

            statement = conn.createStatement();
            rsUser = statement.executeQuery("SELECT idemployees, password FROM employees WHERE idemployees = " + "'" + userName.getText() + "'" + "AND password= " + "'" + password.getText() + "'");
            while (rsUser.next()) {
                userCheck = rsUser.getString("idemployees");
                passCheck = rsUser.getString("password");
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                createStage();
                if (!userCheck.equals(userName.getText()) || !passCheck.equals(password.getText())) {

                    wrongPass.setText("Wrong username or password");
                }
            }

        } catch (SQLException ex) {

        }

    }

    @FXML
    public void createStage() throws IOException {
        m = new Stage();
        changeWindow();

    }

    @FXML
    private void changeWindow() throws IOException {


        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("MainWindow.fxml"));
        Scene trial_scene = new Scene(root);

        m.setScene(trial_scene);
        m.show();

    }

}
