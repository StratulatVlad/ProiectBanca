module com.example.proiectbanca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proiectbanca to javafx.fxml;
    exports com.example.proiectbanca;
}