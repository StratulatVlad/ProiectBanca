package com.example.proiectbanca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class FiscController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textCNP;
    @FXML
    private Button cautaButton;
    @FXML
    private TableView<Clienti> clientiTable;
    @FXML
    private TableColumn<Clienti, String> userCol;
    @FXML
    private TableColumn<Clienti, String> ronCol;
    @FXML
    private TableColumn<Clienti, String> euroCol;
    @FXML
    private TableView<Tranzactii> tranzactiiTable;
    @FXML
    private TableColumn<Tranzactii, String> tipCol;
    @FXML
    private TableColumn<Tranzactii, String> sumaCol;
    @FXML
    private TableColumn<Tranzactii, String> valutaCol;
    @FXML
    private Button stopButton;
    public void stopButtonOnAction(ActionEvent event){
        clientiTable.getItems().clear();
        tranzactiiTable.getItems().clear();
        textCNP.setText(null);
    }

    ObservableList<Clienti> clientiObservableList = FXCollections.observableArrayList();
    ObservableList<Tranzactii> tranzactiiObservableList = FXCollections.observableArrayList();
    public void cautaButtonOnAction(ActionEvent event) throws SQLException{
        DBconnection dBconnection= new DBconnection();
        Connection connection = dBconnection.getConnection();


        PreparedStatement pr = null;
        ResultSet queryResult = null;

        String sql = "SELECT c.username, c.soldRON, c.soldEURO, t.tip_tranzactie, t.suma, t.valuta FROM Clienti c,Tranzactii t where t.cnp = c.cnp and c.cnp="+textCNP.getText();

        try{
            Statement statement=connection.createStatement();
            queryResult= statement.executeQuery(sql);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
            throw new SQLException("SQL Select statemant returns null");

        }
        clientiTable.getItems().clear();
        tranzactiiTable.getItems().clear();
        while(queryResult.next()){
            String username = queryResult.getString("username");
            String soldRON = queryResult.getString("soldRON");
            String soldEURO = queryResult.getString("soldEURO");
            clientiObservableList.add(new Clienti(username, soldRON, soldEURO));
            userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            ronCol.setCellValueFactory(new PropertyValueFactory<>("soldRON"));
            euroCol.setCellValueFactory(new PropertyValueFactory<>("soldEURO"));

            String tip_tranzactie = queryResult.getString("tip_tranzactie");
            String suma = queryResult.getString("suma");
            String valuta = queryResult.getString("valuta");
            tranzactiiObservableList.add(new Tranzactii(tip_tranzactie, suma, valuta));
            tipCol.setCellValueFactory(new PropertyValueFactory<>("tip_tranzactie"));
            sumaCol.setCellValueFactory(new PropertyValueFactory<>("suma"));
            valutaCol.setCellValueFactory(new PropertyValueFactory<>("valuta"));

        }

        clientiTable.setItems(clientiObservableList);
        tranzactiiTable.setItems(tranzactiiObservableList);

    }


    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
