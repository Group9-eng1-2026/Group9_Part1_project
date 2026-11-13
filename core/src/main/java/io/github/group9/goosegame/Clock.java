package io.github.group9.goosegame;

import io.github.group9.goosegame.GeeseUtilities.GeeseSpawner;

// Counts down 5 minute timer, only ticks when update() is called.
public class Clock {
    static final float totalTime = 300f;
    public float timeLeft = totalTime;
    public int wave = 1;
    public float nextWaveTime = 0f;
    public boolean running = false;

    public void update(float deltaTime, World world) {
        if (running) {
            timeLeft -= deltaTime;
            if (timeLeft <= 0) {
                running = false;
            }
        }

        //Handle waves
        if (nextWaveTime <= 0) {
            handleGeese(world, wave*5);
            nextWaveTime = 60f;
            wave++;
        }
        nextWaveTime -= deltaTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    // Resets clock to 5 mins and pauses
    public void reset() {
        timeLeft = totalTime;
        nextWaveTime = 0f;
        wave = 1;
        running = false;
    }

    public float getTimeLeft() {
        return timeLeft;
    }

    public float getTimeRunning() {
        return totalTime - timeLeft;
    }

    // Formats time in Minutes:Seconds format for display
    public String formatTime() {
        int minutes = (int) (timeLeft / 60);
        int seconds = (int) (timeLeft % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void handleGeese(World world, int count){
        for (int i = 0; i < count + 20; i++){
            GeeseSpawner.spawnGoose(world.getPlayer(), world);
        }
    }
}
