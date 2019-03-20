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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 */
public class AdminMenuController implements Initializable {

    String dbName = "project";
    String dbUser = "root";
    String dbPass = "root";
    String space = "  ";
    PreparedStatement pst = null;
    ResultSet rsEmp = null;
    Statement statement = null;
    Connection conn = null;
    ObservableList<String> allEmp = FXCollections.observableArrayList();
    @FXML
    private TextField personalNumber;

    @FXML
    private TextField pNum;

    @FXML
    private Button cmd_delete;

    @FXML
    private Button cmd_add;

    @FXML
    private Button cmd_get;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField password;

    @FXML
    private TextField employmentDate;

    @FXML
    private ListView myList;

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
            System.out.println("Icorrect login details.");
        }
    }

    public void addEmployee() throws SQLException {
            try {

                statement = conn.createStatement();
            
                int executeUpdate = statement.executeUpdate("INSERT into employees values "+" ('" + firstName.getText() + "','" + lastName.getText() + "','" + phoneNumber.getText() + "','" + personalNumber.getText() + "','" + password.getText() + "','" + employmentDate.getText() + "')");
                System.out.println(executeUpdate);
                firstName.clear();
                lastName.clear();
                phoneNumber.clear();
                personalNumber.clear();
                password.clear();
                employmentDate.clear();
                JOptionPane.showMessageDialog(null, "Click ok to continue");


            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Employee addition unsuccessful");
            //e.printStackTrace();
            }
           
    
    }

    public void removeEmployee() throws SQLException {
        try {
            
            statement = conn.createStatement();
            
            int executeUpdate1 = statement.executeUpdate("delete from employees where personalnumber = ('" + pNum.getText() +"')");
            pNum.clear();
         
            JOptionPane.showMessageDialog(null, "click ok to continue");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Employee deletion unsuccessful!!");
        }
    }
    
    public void getEmployees() throws SQLException{
       String newLine = System.getProperty("line.separator");
       allEmp.clear();
       myList.setItems(allEmp);
       statement = conn.createStatement();
       try{
           rsEmp = statement.executeQuery("SELECT * FROM employees");
           while(rsEmp.next()){
               String fName = rsEmp.getString("firstname");
               String lName = rsEmp.getString("lastname");
               String phNum = rsEmp.getString("phonenumber");
               String pnum = rsEmp.getString("personalnumber");
               String pWord = rsEmp.getString("password");
               String eDate = rsEmp.getString("employmentdate");
               
               allEmp.add("Full Name: " + fName + " " + lName + newLine + "Phone number: " + phNum + newLine 
               + "Personal Number: " + pnum + newLine + "Password: " + pWord + newLine + "Employment Date : " + eDate);
           }
       }catch(SQLException ex){
           ex.printStackTrace();
       }
    }

}
