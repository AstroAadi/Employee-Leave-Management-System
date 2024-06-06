module com.example.elms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.elms to javafx.fxml;
    exports com.example.elms;
}