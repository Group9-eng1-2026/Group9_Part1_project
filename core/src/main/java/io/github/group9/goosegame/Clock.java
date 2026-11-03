package io.github.group9.goosegame;

// Counts down 5 minute timer, only ticks when update() is called.
public class Clock {
    static final float totalTime = 300f;
    public float timeLeft = totalTime;
    public boolean running = false;

    //
    public void update(float deltaTime) {
        if (running) {
            timeLeft -= deltaTime;
            if (timeLeft <= 0) {
                running = false;
            }
        }
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
    
}
