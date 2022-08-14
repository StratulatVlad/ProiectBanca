package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class RegisterController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textCNP;
    @FXML
    private TextField textUser;
    @FXML
    private TextField textPass;
    @FXML
    private TextField textEURO;
    @FXML
    private TextField textRON;
    @FXML
    private Button registerButton;
    @FXML
    private Label registerLabel;


    public void registerButtonOnAction(ActionEvent event) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        Connection connection = dBconnection.getConnection();


        PreparedStatement pr = null;


        String sql = "INSERT INTO clienti (cnp, username, password, soldEURO, soldRON) VALUES ("+ textCNP.getText()+", '"+textUser.getText()+"', '"+textPass.getText()+"', "+textEURO.getText()+", "+textRON.getText()+")";

        try {
            Statement statement = connection.createStatement();
            Integer queryResult = statement.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        registerLabel.setText("Ai fost inregistrat! Poti sa te loghezi!");
    }



    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
