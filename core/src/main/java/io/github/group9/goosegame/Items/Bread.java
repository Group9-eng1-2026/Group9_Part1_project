package io.github.group9.goosegame.Items;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.BreadEntity;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

import java.util.List;


/**
 * Class that represents bread Item.
 */
public class Bread extends Item{
    //Constants
    public static final float THROW_DISTANCE = 250f;
    //Lifetime in seconds
    public static final float START_LIFETIME = 10f;

    //Attributes
    private float lifeTime;
    public Vector2 position;
    public BreadEntity entity;

    //Constructor
    public Bread() {
        super("Bread");
    }

    /**
     * Allows for item to be used
     * @param player Player to use item.
     * @param world Where item will affect.
     * @return Boolean value that dictates if item is removed from inventory.
     */
    //Methods
    @Override
    public boolean use(Player player, World world) {
        //If player has none reject
        if (player.getInventory().getSelectedCount() == 0) {
            return false;
        }

        //If bread already deployed reject
        if (world.items.contains(player.getInventory().getSelected())) {
            return false;
        }

        Vector2 playerPos = new Vector2(player.pos.x, player.pos.y);
        Vector2 thisPos = playerPos.add(player.getFacing().scl(THROW_DISTANCE));
        float nx = MathUtils.clamp(thisPos.x, BreadEntity.SIZE / 2f, World.WIDTH);
        float ny = MathUtils.clamp(thisPos.y, BreadEntity.SIZE / 2f, World.HEIGHT);
        this.position = new Vector2(nx, ny);
        this.lifeTime = START_LIFETIME;
        this.entity = new BreadEntity(position);
        world.itemEntities.add(entity);
        return true;
    }

    /**
     * Handles behavior of bread object.
     * @param geese Geese to distract whilst lifetime is above 0.
     * @param player Return focus to once life is 0.
     * @return Boolean value of if to keep Bread Entity in world.
     */
    public boolean update(List<Goose> geese, Player player, float delta) {
        //Decrement lifetime
        lifeTime -= delta;

        if (entity == null) {
            throw new UnsupportedOperationException(
                "This object must run use before running this method");
        }

        if (lifeTime >= 0) {
            entity.distract(geese);
            return true;
        } else {
            player.makeTargetOf(geese);
            return false;
        }
    }
}
