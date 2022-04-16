package com.example.conwaysgameoflifejava;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellColor;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;
import com.example.conwaysgameoflifejava.gameLogic.GameState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {
    public Pane playgroundPane;
    public ResizableCanvas playground;
    public Button startButton;
    public Button stopButton;
    public Button pauseButton;
    public ColorPicker backgroundColorPicker;
    public ColorPicker cellsColorPicker;
    private GameState gameState;
    private ArrayList<ArrayList<Cell>> cells;

    public void initialize() {
        playground.widthProperty().bind(
                playgroundPane.widthProperty());
        playground.heightProperty().bind(
                playgroundPane.heightProperty());

        backgroundColorPicker.setValue(Color.BLACK);

        Platform.runLater(() -> {
            gameState = new GameState(
                playground.getWidth(),
                playground.getHeight()
            );
            cells = gameState.getCells();
            playground.drawPlayground(cells);
        });
    }

    public void startSimulation(ActionEvent actionEvent) {
        gameState.nextGeneration();
        playground.drawPlayground(cells);
    }

    public void stopSimulation(ActionEvent actionEvent) {

    }

    public void pauseSimulation(ActionEvent actionEvent) {

    }

    public void onSetCell(MouseEvent mouseEvent) {
        double posX = mouseEvent.getX();
        double posY = mouseEvent.getY();

        double squarePosX = posX - posX % CellProperty.SIZE.getValue();
        double squarePosY = posY - posY % CellProperty.SIZE.getValue();

        gameState.setAliveCell(squarePosX, squarePosY);
        playground.drawPlayground(cells);
    }

    public void onSetCellColor(ActionEvent actionEvent) {
        CellColor.ALIVE.setColor(cellsColorPicker.getValue());
        if (cells != null) {
            playground.drawPlayground(cells);
        }
    }

    public void onSetBackgroundColor(ActionEvent actionEvent) {
        CellColor.DEAD.setColor(backgroundColorPicker.getValue());
        if (cells != null) {
            playground.drawPlayground(cells);
        }
    }
}