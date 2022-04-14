module com.example.conwaysgameoflifejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.conwaysgameoflifejava to javafx.fxml;
    exports com.example.conwaysgameoflifejava;
    exports com.example.conwaysgameoflifejava.cell;
    opens com.example.conwaysgameoflifejava.cell to javafx.fxml;
    exports com.example.conwaysgameoflifejava.customComponents;
    opens com.example.conwaysgameoflifejava.customComponents to javafx.fxml;
}