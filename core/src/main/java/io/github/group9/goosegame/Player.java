package io.github.group9.goosegame;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public static final float SIZE = 32f;
    private static final float SPEED = 250f;

    public final Vector2 pos;

    public Player(float x, float y) {
        pos = new Vector2(x, y);
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
