module main.controllers.modelowanie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens main to javafx.fxml;
    exports main;
    exports main.controllers;
    opens main.controllers to javafx.fxml;
    exports main.database;
    opens main.database to javafx.fxml;
    exports main.models;
    opens main.models to javafx.fxml;
}