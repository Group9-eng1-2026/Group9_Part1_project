package io.github.group9.goosegame;

import io.github.group9.goosegame.Events.EventManager;
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

// Represents everything that exists in the game world
// Geese, items and hidden events will have to be added here later
public class World {
    // World dimensions
    public static final float WIDTH = 3200f;
    public static final float HEIGHT = 1800f;

    private final Random random = new Random();
    private final Player player;
    public EventManager eventManager = new EventManager();
    public List<Goose> geese = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public List<Entity> itemEntities = new ArrayList<>();

    public World() {
        player = new Player(WIDTH / 2f, HEIGHT / 2f);
        player.getInventory().add(new Bread(), 3);
        player.getInventory().add(new EnergyDrink(), 1);
    }

    public Player getPlayer() {
        return player;
    }

    public void update(float deltaTime, Vector2 movement) {
        //Player
        player.update(deltaTime, movement.x, movement.y, WIDTH, HEIGHT);

        //Geese
        handleGeese(deltaTime, movement);

        //Items
        handleItems(deltaTime);

        //Events
        eventManager.update(this);
    }

    public void handleItems(float deltaTime){
        List<Item> toRemove = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Bread) {
                if (!(((Bread) item).update(geese, player, deltaTime))){
                    itemEntities.remove(((Bread) item).entity);
                    if (random.nextInt(3, 10) > 0) {
                        geese.subList(0, random.nextInt(3, 10)).clear();
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

    public void handleGeese(float deltaTime, Vector2 movement) {
        //Geese Collisions
        for (Goose goose : this.geese){
            //Check intersections with player
            if (goose.intersects(player)) {
                player.exhaustion -= 10;
                //Creates wait time until 10,000 frames have passed before goose can once again move
                goose.recovering = 3f;
            }

            //Move normally
            goose.update(deltaTime, 0f, 0f, WIDTH, HEIGHT);

            //Check for collisions with other geese
            for (Goose other : this.geese) {
                //Skip if both are same
                if (other == goose) { continue; }

                if (other.intersects(goose)) {
                    //Ensure no longer collides with other goose
                    while (goose.intersects(other)){
                        float nx = goose.pos.x + 50f;
                        float ny = goose.pos.y + 50f;
                        if (random.nextBoolean()) { nx *= -1; ny *= -1; }
                        goose.changeTarget(new Player(nx, ny));
                        goose.update(deltaTime, 10f, 10f, WIDTH, HEIGHT);
                    }
                    goose.changeTarget(player);
                }
            }
        }
    }
}
