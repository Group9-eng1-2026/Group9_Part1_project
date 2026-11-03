package io.github.group9.goosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class UserInput {
    private final Vector2 movement = new Vector2();

    // Returns movement direction from WASD or arrow key inputs (-1, 0, or 1 for each axis)
    public Vector2 getInput() {
        movement.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) movement.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) movement.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) movement.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) movement.y -= 1;
        return movement;
    }

    public boolean pausePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
    }

    public boolean restartPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.R);
    }
}
