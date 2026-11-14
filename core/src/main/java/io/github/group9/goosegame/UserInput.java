package io.github.group9.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/** Reads user input from keyboard and returns movement direction and actions */
public class UserInput {
    private final Vector2 movement = new Vector2();

    /** 
     * Gets the player's movement direction based on user input from WASD or arrow keys
     * @return Vector2 movement representing the movement direction, 
     * where x and y values are -1, 0, or 1 for each axis
     */
    public Vector2 getInput() {
        movement.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) movement.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) movement.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) movement.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) movement.y -= 1;
        return movement;
    }

    /** Returns True if SPACE or ESCAPE is pressed */
    public boolean pausePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
    }

    /** Returns True if R is pressed */
    public boolean restartPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.R);
    }

    /** 
     * Checks if a number key (1-5) is pressed
     * @return int - The index of the slot pressed (0-4), or -1 if none was pressed
     */
    public int slotPressed() {
        for (int i = 0; i < 5; i++) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i)) {
                return i;
            }
        }
        return -1;
    }

    /** Returns True if E is pressed */
    public boolean useItemPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.E);
    }
}
