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
    exports main.controllers.admin;
    opens main.controllers.admin to javafx.fxml;
    exports main.bussinessLogic;
    opens main.bussinessLogic to javafx.fxml;
    exports main.controllers.client;
    opens main.controllers.client to javafx.fxml;
    exports main.controllers.templates;
    opens main.controllers.templates to javafx.fxml;
    exports main.controllers.employee;
    opens main.controllers.employee to javafx.fxml;
    exports main.controllers.users;
    opens main.controllers.users to javafx.fxml;
}