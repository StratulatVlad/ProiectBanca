package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Button clientButton;


    public void clientButtonOnAction(ActionEvent event){
            createLoginStage();

    }
    public void createLoginStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("login-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Gestiune Banca - Login Client");
            registrationStage.setScene(new Scene(root,552,465));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}