package com.example.conwaysgameoflifejava;

import com.example.conwaysgameoflifejava.Theme.ThemeSetter;
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
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    public VBox leftVBox;
    public Pane playgroundPane;
    public ResizableCanvas playground;
    public Button startStopButton;
    public Button pauseButton;
    public Button changeThemeButton;
    public ColorPicker backgroundColorPicker;
    public ColorPicker cellsColorPicker;
    public Spinner<Integer> clockIntervalSpinner;
    public Spinner<Integer> spawnSpinner;
    public Spinner<Integer> keepSpinner;
    public Spinner<Integer> hungerSpinner;
    public Spinner<Integer> overpopulationSpinner;
    public Label backgroundColorLabel;
    public Label cellsColorLabel;
    public Label clockIntervalLabel;
    public Label keepLabel;
    public Label overpopulationLabel;
    public Label spawnLabel;
    public Label hungerLabel;
    public Label noCellsLabel;
    private GameState gameState;
    private GameClock gameClock;

    private List<Label> labels = new ArrayList<>();
    private List<Node> buttons = new ArrayList<>();

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

        for (Node node : leftVBox.getChildren()) {
            if (node instanceof Label) {
                labels.add((Label) node);
            } else if (node instanceof Button || node instanceof Spinner || node instanceof ColorPicker) {
                buttons.add(node);
            }
        }
    }

    public void startStopSimulation(ActionEvent actionEvent) {
        if (!gameClock.isRunning()) {
            if (!gameState.isAllCellsDead()) {
                gameClock.start();
                startStopButton.setText("Stop");
                noCellsLabel.setVisible(false);
                noCellsLabel.setManaged(false);
            } else {
                noCellsLabel.setVisible(true);
                noCellsLabel.setManaged(true);
            }
        } else {
            gameClock.stop();
            gameState.clearCells();
            startStopButton.setText("Start");
        }
    }

    public void pauseSimulation(ActionEvent actionEvent) {
        gameClock.stop();
        startStopButton.setText("Start");
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
        ThemeSetter.switchTheme();

        ThemeSetter.setThemeOnVBox(leftVBox);
        for (Node node : buttons) {
            ThemeSetter.setThemeOnNode(node);
        }
        for (Label label : labels) {
            ThemeSetter.setThemeOnLabel(label);
        }
    }
}