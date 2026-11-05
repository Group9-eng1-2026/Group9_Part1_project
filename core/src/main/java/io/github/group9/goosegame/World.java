package io.github.group9.goosegame;

import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Player;

// Represents everything that exists in the game world
// Geese, items and hidden events will have to be added here later
public class World {
    // World dimensions
    public static final float WIDTH = 3200f;
    public static final float HEIGHT = 1800f;

    private final Player player;

    public World() {
        player = new Player(WIDTH / 2f, HEIGHT / 2f);
    }

    public Player getPlayer() {
        return player;
    }

    public void update(float deltaTime, Vector2 movement) {
        player.update(deltaTime, movement.x, movement.y, WIDTH, HEIGHT);
    }
}
