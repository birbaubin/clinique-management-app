module club.management.app {

    opens controllers;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    exports controllers;
    exports dao.models;
    exports views;
    exports start;


}