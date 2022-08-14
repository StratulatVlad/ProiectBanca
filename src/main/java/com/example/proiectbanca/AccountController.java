package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label ronLabel;
    @FXML
    private Label euroLabel;
    @FXML
    private Button depretButton;
    @FXML
    private Button lichidareButton;
    @FXML
    private Label lichidareLabel;

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void depretButtonOnAction(ActionEvent event){
        depRepStage();
    }

    public void depRepStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("tranzactii-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Gestiune Banca - Tranzactii");
            registrationStage.setScene(new Scene(root,552,465));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void lichidareButtonOnAction(ActionEvent event){
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();
        LoginController login = new LoginController();


        String sql=" select soldRON, soldEURO from Clienti where cnp =" +login.getCnp();

        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(sql);
            while(queryResult.next()){
                if(!Objects.equals(queryResult.getString("soldRON"), "0") && !Objects.equals(queryResult.getString("soldEURO"), "0")){
                    lichidareLabel.setText("Pentru a lichida contul trebuie sa ai soldurile 0!");
                }
                else {
                    lichidareStage();
                    Stage stage=(Stage) lichidareButton.getScene().getWindow();
                    stage.close();

                }
                }




        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void lichidareStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("lichidare-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Gestiune Banca - Lichidare");
            registrationStage.setScene(new Scene(root,339,155));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
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
                ronLabel.setText(queryResult.getString("soldRON"));
                euroLabel.setText(queryResult.getString("soldEURO"));
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
