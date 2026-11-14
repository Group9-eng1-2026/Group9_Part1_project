package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import io.github.group9.goosegame.Items.Inventory;
import io.github.group9.goosegame.Items.Item;
import io.github.group9.goosegame.World;

import java.util.List;

/** Represents the player and handles their movement, inventory and interactions */
public class Player extends Entity{
    // Constants
    public static final float SIZE = 32f;
    private static final float SPEED = 250f;
    public static int itemCount = 0;

    // Attributes
    public float exhaustion = 100; // Health bar
    public boolean faster = false;

    private final Inventory inventory = new Inventory();
    private final Vector2 facing = new Vector2(0, 1);

    // Constructor
    public Player(float x, float y) {
        super(x, y, SIZE, SPEED);
    }

    /** 
     * Updates the player's position each frame
     * @param deltaTime Time since the last frame
     * @param dx Horizontal input direction (-1, 0 or 1)
     * @param dy Vertical input direction (-1, 0 or 1)
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world
     */
    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        Vector2 direction = new Vector2(dx, dy);

        // Normalizes speed in all directions
        if (direction.len2() > 0) {
            direction.nor();
            facing.set(direction);
        }

        float speed = SPEED;
        if (faster) { speed += 200f; }

        pos.mulAdd(direction, speed * deltaTime);

        // Keeps player within world boundaries
        float half = SIZE / 2f;
        pos.x = MathUtils.clamp(pos.x, half, worldWidth - half);
        pos.y = MathUtils.clamp(pos.y, half, worldHeight - half);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Vector2 getFacing() {
        return facing;
    }

    /** 
     * Uses the item in the selected inventory slot 
     * then removes it from the inventory 
     */
    public void useSelectedItem(World world) {
        Item item = inventory.getSelected();
        if (item != null && item.use(this, world)) {
            world.items.add(item);
            inventory.removeSelected();
            itemCount++;
        }
    }

    /** Makes all geese in the list chase the player */
    public void makeTargetOf(List<Goose> geese) {
        for (Goose goose : geese) {
            goose.changeTarget(this);
        }
    }
}
