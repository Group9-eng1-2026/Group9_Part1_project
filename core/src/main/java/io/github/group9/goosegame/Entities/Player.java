package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
    public static final float SIZE = 32f;
    private static final float SPEED = 250f;

    public float exhaustion = 100; //Health bar
    public Player(float x, float y) {
        super(x, y, SIZE, SPEED);
    }

    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        Vector2 direction = new Vector2(dx, dy);

        // Maintains constant speed in all directions
        if (direction.len2() > 0) {
            direction.nor();
        }

        pos.mulAdd(direction, SPEED * deltaTime);

        // Keeps player within world boundaries
        float half = SIZE / 2f;
        pos.x = MathUtils.clamp(pos.x, half, worldWidth - half);
        pos.y = MathUtils.clamp(pos.y, half, worldHeight - half);
    }

}
