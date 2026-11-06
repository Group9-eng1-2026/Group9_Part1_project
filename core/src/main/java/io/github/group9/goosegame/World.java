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

        //Geese Collisions
        for (Goose goose : this.geese){
            //Check intersections with player
            if (goose.intersects(player)) {
                player.exhaustion -= 10;
                //Creates wait time until 10,000 frames have passed before goose can once again move
                goose.recovering = 10000;
            }

            //Check for collisions with other geese
            for (Goose other : this.geese) {
                //Skip if both are same
                if (other.equals(goose)) { continue; }

                if (!(other.intersects(goose))) {
                    //Move normally
                    goose.update(deltaTime, 0f, 0f, WIDTH, HEIGHT);
                } else {
                    //Ensure no longer collides with other goose
                    while (other.intersects(goose)){
                        goose.update(deltaTime, 10f, 10f, WIDTH, HEIGHT);
                    }

                    //Make geese wait if they intersect another goose that's not themselves.
                    goose.recovering = 1000;
                }
            }
        }
    }
}
