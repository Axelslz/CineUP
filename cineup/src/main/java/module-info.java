module com.example.cineup {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;

    opens com.example.cineup to javafx.fxml;
    exports com.example.cineup;
    exports com.example.cineup.controllers;
    opens com.example.cineup.controllers to javafx.fxml;
}