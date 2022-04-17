package com.example.conwaysgameoflifejava.gameCore;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameClock {
    private int tick = GameProperty.TICK.getValue();
    private final GameState gameState;
    private ScheduledThreadPoolExecutor executor;
    private boolean running = false;

    public GameClock(GameState gameState) {
        this.gameState = gameState;
        executor = new ScheduledThreadPoolExecutor(1);
    }

    public void start() {
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            gameState.nextGeneration();
            if (gameState.isAllCellsDead()) {
                executor.shutdown();
                running = false;
            }
        }, 0, tick, TimeUnit.MILLISECONDS);
        running = true;
    }

    public void stop() {
        executor.shutdown();
        running = false;
    }

    private void reset() {
        try {
            boolean terminated = executor.awaitTermination((long) (1.5 * tick), TimeUnit.MILLISECONDS);
            if (terminated) {
                running = false;
                start();
                running = true;
            }
        } catch (InterruptedException e) {
            executor.shutdown();
            running = false;
        }
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        boolean wasRunning = !executor.isShutdown();
        this.tick = tick;
        GameProperty.TICK.setValue(tick);

        if (wasRunning) {
            reset();
        }
    }

    public boolean isRunning() {
        return running;
    }
}
