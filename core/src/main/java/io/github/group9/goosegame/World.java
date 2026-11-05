package io.github.group9.goosegame;

import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.Entities.Goose;

import java.util.ArrayList;
import java.util.List;

// Represents everything that exists in the game world
// Geese, items and hidden events will have to be added here later
public class World {
    // World dimensions
    public static final float WIDTH = 3200f;
    public static final float HEIGHT = 1800f;

    private final Player player;
    public List<Goose> geese = new ArrayList<>();

    public World() {
        player = new Player(WIDTH / 2f, HEIGHT / 2f);
    }

    public Player getPlayer() {
        return player;
    }

    public void update(float deltaTime, Vector2 movement) {
        //Player
        player.update(deltaTime, movement.x, movement.y, WIDTH, HEIGHT);

        //Geese
        for (Goose goose : this.geese){
            for (Goose other : this.geese){
                if (other.intersects(goose)) {
                    if (other != goose) {
                        while (other.intersects(goose)){
                            goose.update(deltaTime, 3f, 3f, WIDTH, HEIGHT);
                        }
                        goose.recovering = 100;
                    }
                }
                else {
                    goose.update(deltaTime, 0f, 0f, WIDTH, HEIGHT);
                }
            }
        };
    }
}
