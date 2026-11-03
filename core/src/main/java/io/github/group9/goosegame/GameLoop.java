package io.github.group9.goosegame;

/*
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
*/

public class GameLoop {

    private World world = new World();
    private Clock clock = new Clock();
    private final UserInput input = new UserInput();
    private final RenderScreen renderScreen = new RenderScreen();

    private GameState state = GameState.PAUSED; // Game start paused

    public void render(float deltaTime) {
        handleInput();
        if (state == GameState.PLAYING) {
            update(deltaTime);
        }
        renderScreen.render(world, clock, state);
    }

    private void handleInput() {
        // Pauses and unpauses the game when spacebar is pressed
        if (input.pausePressed()) {
            if (state == GameState.PLAYING) {
                state = GameState.PAUSED;
                clock.stop();
            } else if (state == GameState.PAUSED) {
                state = GameState.PLAYING;
                clock.start();
            }
        }
        if ((state == GameState.ENDED || state == GameState.PAUSED) && input.restartPressed()) {
            restart();
        }
    }

    // Resets the game state, world, and clock to start a new game
    private void restart() {
        world = new World();
        clock.reset();
        state = GameState.PAUSED;
    }

    private void update(float deltaTime) {
        clock.update(deltaTime);
        if (clock.timeLeft <= 0) { 
            state = GameState.ENDED;
            return;
        }
        world.update(deltaTime, input.getInput());
    }

    public void resize(int width, int height) {
        renderScreen.resize(width, height);
    }

    public void dispose() {
        renderScreen.dispose();
    }
}
