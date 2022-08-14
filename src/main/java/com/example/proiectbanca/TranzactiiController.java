package com.example.proiectbanca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class TranzactiiController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox tipCombo;
    @FXML
    private ChoiceBox valutaCombo;
    @FXML
    private TextField textSuma;
    @FXML
    private Button confirmaButton;

    ObservableList<String> tip = FXCollections.observableArrayList("Depozit","Retragere");
    ObservableList<String> valuta = FXCollections.observableArrayList("RON","EURO");

    public void confirmaButtonOnAction(ActionEvent event){
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();
        LoginController login = new LoginController();

        String sql = null;
        if(tipCombo.getValue().toString() == "Depozit"){
            if(valutaCombo.getValue().toString() == "RON"){
                sql=" update Clienti c,Tranzactii t set c.soldRON = c.soldRON + "+textSuma.getText()+"  where  t.cnp = c.cnp and c.cnp =" +login.getCnp();
            }
            else if(valutaCombo.getValue().toString() == "EURO"){
                sql=" update Clienti c,Tranzactii t set c.soldEURO = c.soldEURO + "+textSuma.getText()+"  where  t.cnp = c.cnp and c.cnp =" +login.getCnp();
            }
        }
        if(tipCombo.getValue().toString() == "Retragere"){
            if(valutaCombo.getValue().toString() == "RON"){
                sql=" update Clienti c,Tranzactii t set c.soldRON = c.soldRON - "+textSuma.getText()+"  where  t.cnp = c.cnp and c.cnp =" +login.getCnp();
            }
            else if(valutaCombo.getValue().toString() == "EURO"){
                sql=" update Clienti c,Tranzactii t set c.soldEURO = c.soldEURO - "+textSuma.getText()+"  where  t.cnp = c.cnp and c.cnp =" +login.getCnp();
            }
        }

        String sql2 = "INSERT INTO tranzactii (id, cnp, tip_tranzactie, suma, valuta) VALUES (null, '"+login.getCnp()+"', '"+tipCombo.getValue().toString()+"', "+textSuma.getText()+", '"+valutaCombo.getValue().toString()+"')";



        try {
            Statement statement=connectionDatabase.createStatement();
            Integer queryResult= statement.executeUpdate(sql);
            Integer queryResult2= statement.executeUpdate(sql2);
            Stage stage=(Stage) confirmaButton.getScene().getWindow();
            stage.close();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipCombo.setItems(tip);
        valutaCombo.setItems(valuta);
    }
}
