package io.github.group9.goosegame;

import io.github.group9.goosegame.Events.EventManager;
import io.github.group9.goosegame.GeeseUtilities.GeeseSpawner;
import io.github.group9.goosegame.Items.Bread;
import io.github.group9.goosegame.Items.EnergyDrink;
import io.github.group9.goosegame.Items.Item;
import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Entity;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.Entities.Goose;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

/** Holds everything in the game world, including:
 *  the player, geese, items and events
 */
public class World {
    // Constants
    public static final float SEPARATE = 2f;
    // World dimensions
    public static final float WIDTH = 3200f;
    public static final float HEIGHT = 1800f;
    
    // Lists of entities in the world
    public List<Goose> geese = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public List<Entity> itemEntities = new ArrayList<>();

    private final Random random = new Random();
    private final Player player;
    public EventManager eventManager = new EventManager();

    // Constructor
    public World() {
        player = new Player(WIDTH / 2f, HEIGHT / 2f);
        // Add initial items to the inventory
        player.getInventory().add(new Bread(), 3);
        player.getInventory().add(new EnergyDrink(), 1);
    }

    public Player getPlayer() { return player; }

    /** 
     * Updates the player, geese, items and events for each frame
     * @param deltaTime Time since the last frame
     * @param movement Player's movement direction
     */
    public void update(float deltaTime, Vector2 movement) {
        //Player
        player.update(deltaTime, movement.x, movement.y, WIDTH, HEIGHT);

        //Geese
        handleGeese(deltaTime);

        //Items
        handleItems(deltaTime);

        //Events
        eventManager.update(this);
    }

    /** 
     * Updates active items and removes the ones that are finished
     * @param deltaTime Time since the last frame
     */
    public void handleItems(float deltaTime){
        List<Item> toRemove = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Bread) {
                if (!(((Bread) item).update(geese, player, deltaTime))){
                    itemEntities.remove(((Bread) item).entity);
                    if (random.nextInt(3, 10) > 0) {
                        int max = random.nextInt(3, 10);
                        max = min(max, geese.size());
                        geese.removeAll(((Bread) item).closestGeese(geese, max)); // Removes the geese closest to the bread
                        GeeseSpawner.geeseDispersed += max;
                    }
                    toRemove.add(item);
                }
            }
            if (item instanceof EnergyDrink) {
                if(((EnergyDrink) item).update(player, deltaTime)) {
                    toRemove.add(item);
                }
            }
        }
        items.removeAll(toRemove);
    }

    /** 
     * Handles geese movement and collisions with the player and other geese
     * @param deltaTime Time since the last frame
     */
    public void handleGeese(float deltaTime) {
        Vector2 separate = new Vector2();
        //Geese Collisions
        for (Goose goose : this.geese){
            //If goose collides with the player, decrease player's exhaustion by 10
            if (goose.intersects(player) && goose.recovering <= 0) {
                player.exhaustion -= 10;
                //Creates wait time until 3s have passed before goose can once again move
                goose.recovering = 3f;
            }

            //Move normally
            goose.update(deltaTime, 0f, 0f, WIDTH, HEIGHT);

            //Check for collisions with other geese
            for (Goose other : this.geese) {
                //Skip if both are same or if they aren't colliding
                if (other == goose || !goose.intersects(other)) { continue; }

                //Push goose away from the one it overlaps
                separate.set(goose.pos).sub(other.pos);
                if (separate.isZero()) { separate.setToRandomDirection(); }
                goose.pos.mulAdd(separate.nor(), SEPARATE);
            }
        }
    }
}
