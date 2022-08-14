package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class LichidareController {
    @FXML
    private Button noButton;
    @FXML
    private Button yesButton;
    public void yesButtonOnAction(ActionEvent event){
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();
        LoginController login = new LoginController();


        String sql=" delete from Clienti where soldRON = 0 and soldEURO = 0 and cnp = "+login.getCnp();

        try {
            Statement statement=connectionDatabase.createStatement();
            Integer queryResult= statement.executeUpdate(sql);
            Stage stage=(Stage) yesButton.getScene().getWindow();
            stage.close();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void noButtonOnAction(ActionEvent e){
        Stage stage=(Stage) noButton.getScene().getWindow();
        stage.close();
    }

}
