module club.management.app {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    exports sample.controllers;
    exports sample.dao.models;

    opens sample;
}