package com.example.proiectbanca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InboxController implements Initializable {

    @FXML
    private Button okButton;

    @FXML
    private TableView<Tranzactii> tranzactiiTable;
    @FXML
    private TableColumn<Tranzactii, String> cnpCol;
    @FXML
    private TableColumn<Tranzactii, String> tipCol;
    @FXML
    private TableColumn<Tranzactii, String> sumaCol;
    @FXML
    private TableColumn<Tranzactii, String> valutaCol;
    ObservableList<Tranzactii> tranzactiiObservableList = FXCollections.observableArrayList();
    public void okButtonOnAction(ActionEvent event){
        Stage stage=(Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBconnection connectionNow= new DBconnection();
        Connection connectionDatabase= connectionNow.getConnection();

        String sql = "SELECT  * FROM Tranzactii ORDER BY id DESC";

        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(sql);
            while(queryResult.next()){
                String cnp = queryResult.getString("cnp");
                String tip_tranzactie = queryResult.getString("tip_tranzactie");
                String suma = queryResult.getString("suma");
                String valuta = queryResult.getString("valuta");
                tranzactiiObservableList.add(new Tranzactii(cnp, tip_tranzactie, suma, valuta));
                cnpCol.setCellValueFactory(new PropertyValueFactory<>("cnp"));
                tipCol.setCellValueFactory(new PropertyValueFactory<>("tip_tranzactie"));
                sumaCol.setCellValueFactory(new PropertyValueFactory<>("suma"));
                valutaCol.setCellValueFactory(new PropertyValueFactory<>("valuta"));

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        tranzactiiTable.setItems(tranzactiiObservableList);

    }

}

