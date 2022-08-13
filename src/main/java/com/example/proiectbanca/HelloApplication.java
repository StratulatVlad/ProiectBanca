package com.example.proiectbanca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primarystage.setScene(new Scene(root, 540, 453));
        primarystage.setTitle("Gestiune Banca - Home");
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}