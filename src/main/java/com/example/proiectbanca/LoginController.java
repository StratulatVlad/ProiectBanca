package com.example.proiectbanca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField textUser;
    @FXML
    private PasswordField textPass;
    @FXML
    private Label errorLabel;

    public static String cnp;

    public  String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp){
        this.cnp=cnp;
    }

    public void loginButtonOnAction(ActionEvent event){
        if(!textUser.getText().isBlank() && !textPass.getText().isBlank()){
            errorLabel.setText("Complete fields!");
            if(validateLogin(textUser.getText(),textPass.getText()))
            {
                errorLabel.setText("Welcome!");
                AccountStage();


            }else
                errorLabel.setText("Invalid Login!");

        }else{
            errorLabel.setText("Complete fields!");
        }
    }
    public void AccountStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("account-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Gestiune Banca - Client");
            registrationStage.setScene(new Scene(root,552,465));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public boolean validateLogin(String usernameTextField,String passwordPasswordField){
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();
        boolean cnt=false;

        String verifyLogin=" Select cnp from Clienti where username='"+usernameTextField+"' and password = '"+ passwordPasswordField+"'";
        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(!queryResult.getString("cnp").isBlank()){
                    cnt=true;
                    cnp = queryResult.getString("cnp");
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(cnt)
            return true;
        else
            return false;
    }

    public void registerButtonOnAction(ActionEvent event){
        registerStage();
    }
    public void registerStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("register-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Gestiune Banca - Register");
            registrationStage.setScene(new Scene(root,552,465));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
