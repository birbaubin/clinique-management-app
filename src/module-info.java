module club.management.app {

    opens controllers;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires org.junit.jupiter.api;

    exports controllers;
    exports dao.models;
    exports start;


}