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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class MainWindowController implements Initializable {

    Connection conn = null;
    String dbName = "newproject";
    String dbUser = "root";
    String dbPass = "root";
    String scannedItem = null;
    String space = "  ";
    int totalprice = 0;
    int scannedItemPrice = 0;
    int currentCredit = 0;
    int newCredit = 0;
    String id = null;
    boolean correctPass;
    String adminPass = "admin";

    @FXML
    private PasswordField password;

    @FXML
    private TextField pNum;

    @FXML
    private Button adminLogin;

    @FXML
    private Stage adminStage;

    @FXML
    private Label wrongPass;

    @FXML
    private TextField searchItem2;

    @FXML
    private Button mainAddItem2;

    @FXML
    private Stage newStage3;

    @FXML
    private TextField searchMemberTextField;

    @FXML
    private ListView memberPrintTextBox;

    @FXML
    private TextField searchItem;

    @FXML
    private Button clearListButton;

    @FXML
    private Button mainAddItem;

    @FXML
    private Stage newStage;

    @FXML
    private TextField inputItemField;

    @FXML
    private Label itemLabel;

    @FXML
    private Button addButton;

    @FXML
    private ListView listofitems;

    @FXML
    private Button removeButton;

    @FXML
    private Label totalSumLabel;

    @FXML
    private TextField custIdField;

    @FXML
    private Button mainRemoveItem;

    @FXML
    private Stage newStage1;

    @FXML
    private Stage newStage2;

    @FXML
    private Stage newStage4;

    @FXML
    private TextField scanItemField;

    @FXML
    private ListView itemsList;

    @FXML
    private TextField getItemId;

    @FXML
    private Button searchIdField;

    @FXML
    private Button searchAllCust;

    @FXML
    private Button removeCustButton;

    ObservableList<String> items = FXCollections.observableArrayList();
    ObservableList<String> allItems = FXCollections.observableArrayList();
    ObservableList<String> customerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkDbConn();
        listofitems.setItems(items);
        itemLabel.setText("");
        totalSumLabel.setText("");
        listofitems.setMouseTransparent(true);
        memberPrintTextBox.setItems(customerList);
    }

    @FXML
    public void createNewMember() throws IOException {
        newStage = new Stage();
        mainNewMember();
    }

    public void mainNewMember() throws IOException {
        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("NewMember.fxml"));
        Scene addItem = new Scene(root);

        newStage.setScene(addItem);
        newStage.show();
    }

    @FXML
    public void createAddItemWindow() throws IOException {
        newStage1 = new Stage();
        mainAddItem();
    }

    @FXML
    public void createRemoveItemWindow() throws IOException {
        newStage2 = new Stage();
        mainRemoveItem();

    }

    @FXML
    public void createRemoveCustWindow() throws IOException {
        newStage4 = new Stage();
        mainRemoveCust();

    }

    public void mainAddItem() throws IOException {
        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("AddItemWindow.fxml"));
        Scene addItem = new Scene(root);

        newStage1.setScene(addItem);
        newStage1.show();
    }

    public void checkDbConn() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + dbName
                    + "?user=" + dbUser + "&password=" + dbPass);
        } catch (SQLException ex) {

        }
    }

    public void mainRemoveItem() throws IOException {
        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("RemoveItem.fxml"));
        Scene removeItem = new Scene(root);

        newStage2.setScene(removeItem);
        newStage2.show();
    }

    public void mainRemoveCust() throws IOException {
        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("RemoveCus.fxml"));
        Scene removeCust = new Scene(root);
        newStage4.setScene(removeCust);
        newStage4.show();
    }

    public void itemList() throws SQLException {
        checkDbConn();
        ResultSet rsItems = null;
        allItems.clear();
        itemsList.setItems(allItems);
        String newLine = System.getProperty("line.separator");
        Statement statement = conn.createStatement();
        try {
            rsItems = statement.executeQuery("SELECT * FROM products");
            while (rsItems.next()) {
                allItems.add("Name: "+rsItems.getString("name") + newLine + "Id:" + rsItems.getString("id") + newLine + "Manufacturer:" + rsItems.getString("manufacturer") + newLine + "Price:" + rsItems.getString("sellprice")
                        + newLine + "Purchase price:" + rsItems.getString("purchaseprice") + newLine + "Number in stock:" + rsItems.getString("numberofpurchases") + newLine + "Time of purchase:" + rsItems.getString("purchasetime"));
                System.out.println(rsItems.getString("name"));
            }
        } catch (SQLException e) {

        }

    }

    public void itemIdField() throws SQLException {
        checkDbConn();
        ResultSet rsItem = null;

        allItems.clear();
        itemsList.setItems(allItems);

        Statement statement = conn.createStatement();
        try {
            rsItem = statement.executeQuery("SELECT * FROM products where id ='" + getItemId.getText() + "';");
            while (rsItem.next()) {
                allItems.add(rsItem.getString("name") + space + rsItem.getString("id") + space + rsItem.getString("manufacturer") + space + rsItem.getString("sellprice")
                        + space + rsItem.getString("purchaseprice") + space + rsItem.getString("numberofpurchases") + space + rsItem.getString("purchasetime"));
            }
        } catch (SQLException e) {
        }
    }

    public void scanItem() throws IOException {
        Statement statement = null;
        ResultSet rsItem = null;
        try {
            statement = conn.createStatement();
            rsItem = statement.executeQuery("SELECT name,sellprice,id FROM products WHERE id = " + "'" + inputItemField.getText() + "'");
            while (rsItem.next()) {
                itemLabel.setText(rsItem.getString("name"));
                scannedItem = itemLabel.getText();
                scannedItemPrice = Integer.parseInt(rsItem.getString("sellprice"));
                id = rsItem.getString("id");
            }
        } catch (SQLException ex) {
        }
    }

    public void addButton() {

        if (scannedItem != null) {
            items.add(id + space + scannedItem + space + scannedItemPrice + ":-");
            totalprice = totalprice + scannedItemPrice;
            totalSumLabel.setText(Integer.toString(totalprice) + ":-");
        }
    }

    public void removeButton() {
        if (items.contains(id + space + scannedItem + space + scannedItemPrice + ":-")) {
            totalprice = totalprice - scannedItemPrice;
            totalSumLabel.setText(Integer.toString(totalprice) + ":-");
            items.remove(id + space + scannedItem + space + scannedItemPrice + ":-");
        }
    }

    public void checkoutButton() {
        if (custIdField != null) {
            Statement statement = null;
            ResultSet rsCurrentCredits = null;
            try {
                statement = conn.createStatement();
                rsCurrentCredits = statement.executeQuery("SELECT credits FROM customers WHERE personalnumber = " + "'" + custIdField.getText());
                while (rsCurrentCredits.next()) {
                    currentCredit = Integer.parseInt(rsCurrentCredits.getString("credits"));
                }
            } catch (SQLException ex) {
            }
            try {
                statement = conn.createStatement();
                currentCredit = currentCredit + totalprice;
                int update = statement.executeUpdate("UPDATE customers SET credits = '" + (Integer.toString(currentCredit)) + "' where personalnumber = '" + custIdField.getText() + "'");
            } catch (SQLException e) {
            }
            items.clear();
            totalprice = 0;
            totalSumLabel.setText("");
            custIdField.setText("");
            inputItemField.clear();
            scannedItem = null;
        }
    }

    public void searchMember() throws IOException {
        Statement statement = null;
        ResultSet rs = null;
        customerList.clear();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM customers WHERE personalnumber=" + "'" + searchMemberTextField.getText() + "'");
            while (rs.next()) {
                //System.out.println(rs.getString("firstname") + " " + rs.getString("lastname"));
                //memberPrintTextBox.
                customerList.clear();
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String personalNumber = rs.getString("personalnumber");
                String adress = rs.getString("adress");
                String phoneNumber = rs.getString("phonenumber");
                String credits = rs.getString("credits");
                String newLine = System.getProperty("line.separator");
                customerList.add("Name: " + firstName + " " + lastName + newLine + "Personal number: " + personalNumber + newLine + "Adress: " + adress + newLine + "Phone number:  " + phoneNumber + newLine + "Credits: " + credits);
            }
        } catch (SQLException ex) {
        } finally {
            // Close the ResultSet and the Statement
        }
    }

    public void displayAllCust() throws SQLException {
        String newLine = System.getProperty("line.separator");
        customerList.clear();
        ResultSet rsAllCust = null;
        Statement statement = conn.createStatement();
        try {
            rsAllCust = statement.executeQuery("SELECT * FROM customers");
            while (rsAllCust.next()) {
                String firstName = rsAllCust.getString("firstname");
                String lastName = rsAllCust.getString("lastname");
                String personalNumber = rsAllCust.getString("personalnumber");
                String adress = rsAllCust.getString("adress");
                String phoneNumber = rsAllCust.getString("phonenumber");
                String credits = rsAllCust.getString("credits");
                customerList.add("Name: " + firstName + " " + lastName + newLine + "Personal number: " + personalNumber + newLine + "Adress: " + adress + newLine + "Phone number:  " + phoneNumber + newLine + "Credits: " + credits);
            }
        } catch (SQLException e) {

        }

    }

    public void clearList() {
        items.clear();
        totalprice = 0;
        totalSumLabel.setText("");
        custIdField.setText("");
    }

    public void adminLogin() throws IOException {
        Statement statement = null;
        ResultSet rsUser = null;
        try {
            statement = conn.createStatement();
            rsUser = statement.executeQuery("SELECT personalnumber, password FROM employees WHERE personalnumber= " + "'" + pNum.getText() + "'" + "AND password= " + "'" + password.getText() + "'");
            while (rsUser.next()) {
                if (rsUser.getString("password").equals(adminPass)) {
                    createAdminStage();
                }
            }

        } catch (SQLException ex) {

        }

    }

    public void createAdminStage() throws IOException {
        adminStage = new Stage();
        changeToAdminWindow();

    }

    @FXML
    private void changeToAdminWindow() throws IOException {

        AnchorPane root = FXMLLoader.load(ProjectB.class.getResource("adminMenu.fxml"));
        Scene adminScene = new Scene(root);

        adminStage.setScene(adminScene);
        adminStage.show();
    }

    private void wrongPassLabel() {
        correctPass = true;
        if (correctPass) {
            wrongPass.setVisible(false);
        }
        if (correctPass == false) {
            wrongPass.setVisible(true);
        }
    }
}
