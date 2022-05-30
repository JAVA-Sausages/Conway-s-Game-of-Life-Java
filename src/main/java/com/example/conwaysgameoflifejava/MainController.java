package com.example.conwaysgameoflifejava;

import com.example.conwaysgameoflifejava.Theme.ThemeSetter;
import com.example.conwaysgameoflifejava.cell.CellColor;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.cell.CellRule;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;
import com.example.conwaysgameoflifejava.gameCore.GameClock;
import com.example.conwaysgameoflifejava.gameCore.GameProperty;
import com.example.conwaysgameoflifejava.gameCore.GameState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {
    public VBox leftVBox;
    public Pane playgroundPane;
    public ResizableCanvas playground;
    public Button startStopButton;
    public Button pauseButton;
    public Button changeThemeButton;
    public ColorPicker backgroundColorPicker;
    public ColorPicker cellsColorPicker;
    public Spinner<Integer> spawnSpinner;
    public Spinner<Integer> keepSpinner;
    public Label backgroundColorLabel;
    public Label cellsColorLabel;
    public Label keepLabel;
    public Label spawnLabel;
    public Label noCellsLabel;
    private GameState gameState;
    private GameClock gameClock;

    private final List<Label> labels = new ArrayList<>();
    private final List<Node> buttons = new ArrayList<>();

    public void initialize() {
        spawnSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            CellRule.SPAWN.setValue(newValue);
        });
        keepSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            CellRule.KEEP.setValue(newValue);
        });

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

    public void onStartStopSimulation(ActionEvent actionEvent) {
        if (!gameClock.isRunning()) {
            if (!gameState.allCellsDead.getValue()) {
                gameClock.start();
                startStopButton.setText("Stop");
                noCellsLabel.setVisible(false);
                noCellsLabel.setManaged(false);
            } else if(Objects.equals(startStopButton.getText(), "Start")) {
                noCellsLabel.setVisible(true);
                noCellsLabel.setManaged(true);
            } else {
                gameClock.stop();
                gameState.clearCells();
                startStopButton.setText("Start");
            }
        } else {
            gameClock.stop();
            gameState.clearCells();
            startStopButton.setText("Start");
        }
    }

    public void onPauseSimulation(ActionEvent actionEvent) {
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

    public void onChangeTheme(ActionEvent actionEvent) {
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