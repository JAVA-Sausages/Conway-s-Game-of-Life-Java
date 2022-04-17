package com.example.conwaysgameoflifejava;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellColor;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;
import com.example.conwaysgameoflifejava.gameCore.GameClock;
import com.example.conwaysgameoflifejava.gameCore.GameState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    private GameClock gameClock;

    public void initialize() {
        playground.widthProperty().bind(
                playgroundPane.widthProperty());
        playground.heightProperty().bind(
                playgroundPane.heightProperty());

        backgroundColorPicker.setValue(Color.BLACK);

        Platform.runLater(() -> {
            gameState = new GameState(playground);
            gameClock = new GameClock(gameState);
        });
    }

    public void startSimulation(ActionEvent actionEvent) {
        if (!gameState.isAllCellsDead()) {
            gameClock.start();
        }
    }

    public void stopSimulation(ActionEvent actionEvent) {
        gameClock.stop();
        gameState.clearCells();
    }

    public void pauseSimulation(ActionEvent actionEvent) {
        gameClock.stop();
    }

    public void onSetCell(MouseEvent mouseEvent) {
        if (!gameClock.isRunning()) {
            double posX = mouseEvent.getX();
            double posY = mouseEvent.getY();

            double cellPosX = posX - posX % CellProperty.SIZE.getValue();
            double cellPosY = posY - posY % CellProperty.SIZE.getValue();

            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                gameState.setDeadCell(cellPosX, cellPosY);
            } else {
                gameState.setAliveCell(cellPosX, cellPosY);
            }
        }
    }

    public void onSetCellColor(ActionEvent actionEvent) {
        CellColor.ALIVE.setColor(cellsColorPicker.getValue());
        gameState.render();
    }

    public void onSetBackgroundColor(ActionEvent actionEvent) {
        CellColor.DEAD.setColor(backgroundColorPicker.getValue());
        gameState.render();
    }
}