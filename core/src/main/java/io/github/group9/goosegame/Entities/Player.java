package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import io.github.group9.goosegame.Inventory;
import io.github.group9.goosegame.Entities.Item;
import io.github.group9.goosegame.World;

public class Player extends Entity{
    public static final float SIZE = 32f;
    private static final float SPEED = 250f;

    public float exhaustion = 100; //Health bar

    private final Inventory inventory = new Inventory();
    private final Vector2 facing = new Vector2(0, 1);

    public Player(float x, float y) {
        super(x, y, SIZE, SPEED);
    }

    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        Vector2 direction = new Vector2(dx, dy);

        // Normalizes speed in all directions
        if (direction.len2() > 0) {
            direction.nor();
            facing.set(direction);
        }

        pos.mulAdd(direction, SPEED * deltaTime);

        // Keeps player within world boundaries
        float half = SIZE / 2f;
        pos.x = MathUtils.clamp(pos.x, half, worldWidth - half);
        pos.y = MathUtils.clamp(pos.y, half, worldHeight - half);
    }

    public Inventory getInventory() {
        return inventory;
    }

    // Returns direction the player is facing
    public Vector2 getFacing() {
        return facing;
    }

    public void useSelectedItem(World world) {
        Item item = inventory.getSelected();
        if (item != null && item.use(this, world)) {
            inventory.removeSelected();
        }
    }

}
