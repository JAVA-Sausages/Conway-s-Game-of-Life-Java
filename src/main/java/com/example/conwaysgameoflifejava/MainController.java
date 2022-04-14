package com.example.conwaysgameoflifejava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MainController {

    public void initialize() {
        ResizableCanvas canvas = new ResizableCanvas();
        playgroundPane.getChildren().add(canvas);
        canvas.widthProperty().bind(
                playgroundPane.widthProperty());
        canvas.heightProperty().bind(
                playgroundPane.heightProperty());
    }
    public Pane playgroundPane;

    public void startSimulation(ActionEvent actionEvent) {
    }

    public void stopSimulation(ActionEvent actionEvent) {
    }

    public void pauseSimulation(ActionEvent actionEvent) {
    }
}