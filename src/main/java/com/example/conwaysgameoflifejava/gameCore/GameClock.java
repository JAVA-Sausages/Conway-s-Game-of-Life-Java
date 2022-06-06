package com.example.conwaysgameoflifejava.gameCore;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameClock {
    private final int tick = GameProperty.TICK.getValue();
    private final GameState gameState;
    private ScheduledThreadPoolExecutor executor;
    private boolean running = false;

    public GameClock(GameState gameState) {
        this.gameState = gameState;
        executor = new ScheduledThreadPoolExecutor(1);
    }

    public void start() {
        if (!running) {
            executor = new ScheduledThreadPoolExecutor(1);
            executor.scheduleAtFixedRate(() -> {
                if (gameState.allCellsDead.getValue()) {
                    executor.shutdown();
                    running = false;
                } else {
                    gameState.nextGeneration();
                }
            }, 0, tick, TimeUnit.MILLISECONDS);
            running = true;
        }
    }

    public void stop() {
        if (running) {
            executor.shutdown();
            running = false;
        }
    }

    public boolean isNotRunning() {
        return !running;
    }
}
