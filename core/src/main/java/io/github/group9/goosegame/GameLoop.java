package io.github.group9.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


/* Add when textures are implemented:
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
*/

import io.github.group9.goosegame.GeeseUtilities.GeeseSpawner;

public class GameLoop {

    private World world = new World();
    private Clock clock = new Clock();
    private final UserInput input = new UserInput();
    private final RenderScreen renderScreen = new RenderScreen();

    private GameState state = GameState.PAUSED; // Game start paused

    public void render(float deltaTime) {
        //Handle player
        handleInput();
        if (state == GameState.PLAYING) {
            update(deltaTime);
            //Handle Geese
            handleGeese();
        }
        renderScreen.render(world, clock, state);
    }

    public void handleGeese(){
        if (GeeseSpawner.getGooseCount() < 30){
            GeeseSpawner.spawnGoose(world.getPlayer(), world);
        }
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
        if ((state == GameState.WIN || state == GameState.LOSE || state == GameState.PAUSED) && input.restartPressed()) {
            restart();
        }

        // Allows user to select inventory slots when game is unpaused
        if (state == GameState.PLAYING) {
            int slot = input.slotPressed();
            if (slot >= 0) {
                world.getPlayer().getInventory().select(slot);
            }
            // Uses item when E is pressed
            if (input.useItemPressed()) {
                world.getPlayer().useSelectedItem(world);
            }
        }
    }

    // Resets the game state, world, and clock to start a new game
    private void restart() {
        world = new World();
        clock.reset();
        state = GameState.PAUSED;
        GeeseSpawner.reset();
    }

    // Updates clock, ends game when timer reaches 0
    private void update(float deltaTime) {
        clock.update(deltaTime, world);
        if (clock.timeLeft <= 0) {
            state = GameState.LOSE;
            return;
        }

        // Player wins if item count is >= 1 (Replace with win condition)
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            state = GameState.WIN;
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
