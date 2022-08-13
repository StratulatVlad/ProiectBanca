package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label ronLabel;
    @FXML
    private Label euroLabel;
    @FXML
    private Button deprepButton;
    @FXML
    private Button lichidareButton;

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();
        LoginController login = new LoginController();

        String sql=" Select soldEURO, soldRON from Clienti where cnp = "+login.getCnp();

        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(sql);
            while(queryResult.next()){
                ronLabel.setText(queryResult.getString("soldRON") + " RON");
                euroLabel.setText(queryResult.getString("soldEURO") + " EURO");
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
