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

public class UpdateController implements Initializable {

    @FXML
    private Button okButton;
    @FXML
    private Label updatelabel;
    public void okButtonOnAction(ActionEvent event){
        Stage stage=(Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();

        String sql = "SELECT  * FROM Tranzactii ORDER BY id DESC limit 1";

        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(sql);
            while(queryResult.next()){
                      updatelabel.setText("Update!\n Clientul cu cnp-ul "+ queryResult.getString("cnp")+" tocmai a facut o \ntranzactie de tip '"+queryResult.getString("tip_tranzactie")+"' in valoare de "+queryResult.getString("suma")+" "+queryResult.getString("valuta")+" ");
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

}

