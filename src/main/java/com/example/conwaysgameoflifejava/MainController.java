package com.example.conwaysgameoflifejava;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellColor;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;
import com.example.conwaysgameoflifejava.gameCore.GameClock;
import com.example.conwaysgameoflifejava.gameCore.GameState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;

import java.util.ArrayList;

public class MainController {
    public Pane playgroundPane;
    public ResizableCanvas playground;
    public Button startButton;
    public Button stopButton;
    public Button pauseButton;
    public Button changeThemeButton;
    public ColorPicker backgroundColorPicker;
    public ColorPicker cellsColorPicker;
    public VBox leftVBox;
    public Label backgroundColorLabel;
    public Label cellsColorLabel;
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

//        leftVBox.setStyle("-fx-background-color: RED");

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

    public void changeTheme(ActionEvent actionEvent) {
        Background bg = leftVBox.getBackground();
        List<BackgroundFill> fills = bg.getFills();
        System.out.println(bg);
        BackgroundFill col = fills.get(0);
        Paint paint = col.getFill();

        String hexaColor = paint.toString();
        System.out.printf(hexaColor);
        String expectedColor = "0xd4d4d4ff";

        if (expectedColor.equals(hexaColor)) {
            leftVBox.setStyle("-fx-background-color: '303030'");
            setLabelsTextColor("ffffff", backgroundColorLabel, cellsColorLabel);
            setButtonsStyle("417DF9", startButton, stopButton, pauseButton, changeThemeButton, cellsColorPicker, backgroundColorPicker);
        } else {
            leftVBox.setStyle("-fx-background-color: 'D4D4D4'");
            setLabelsTextColor("000000", backgroundColorLabel, cellsColorLabel);
            setButtonsStyle("303030", startButton, stopButton, pauseButton, changeThemeButton,cellsColorPicker, backgroundColorPicker);
        }
    }

    private void setLabelsTextColor(String color, Label... labels) {
        color = "#" + color;
        for (Label label : labels) {
            label.setStyle("-fx-text-fill: " + color + "");
        }
    }

    private void setButtonsStyle(String backgroundColor, Node... buttons) {
        backgroundColor = "#" + backgroundColor;
        for (Node button : buttons) {
            button.setStyle("-fx-background-color: " + backgroundColor + "");
        }
    }
}