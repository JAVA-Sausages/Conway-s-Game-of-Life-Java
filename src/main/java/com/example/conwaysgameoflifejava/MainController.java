package com.example.conwaysgameoflifejava;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController {
    public Pane playgroundPane;
    public ResizableCanvas playground;

    private Cell[][] cells;

    public void initialize() {
        playground.widthProperty().bind(
                playgroundPane.widthProperty());
        playground.heightProperty().bind(
                playgroundPane.heightProperty());

        double playgroundWidth = playground.getWidth();
        double playgroundHeight = playground.getHeight();

        int cellsXCount = (int) playgroundWidth / CellProperty.SIZE.getValue();
        int cellsYCount = (int) playgroundHeight / CellProperty.SIZE.getValue();

        cells = new Cell[cellsXCount][cellsYCount];

        for (int i = 0; i < cellsXCount; i++) {
            for (int j = 0; j < cellsYCount; j++) {
                cells[i][j] = new Cell(i * CellProperty.SIZE.getValue(),
                        j * CellProperty.SIZE.getValue());
            }
        }
    }

    public void startSimulation(ActionEvent actionEvent) {

    }

    public void stopSimulation(ActionEvent actionEvent) {

    }

    public void pauseSimulation(ActionEvent actionEvent) {

    }

    public void setCell(MouseEvent mouseEvent) {

    }
}