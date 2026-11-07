package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Class that holds physical presence of bread object.
 */
public class BreadEnitity extends Entity {
    //Constants
    public static final float SIZE = 24f;
    public static final float SPEED = 0f;

    //Constructor
    public BreadEnitity(Vector2 pos) {
        super(pos.x, pos.y, SIZE, SPEED);
    }

    //Methods
    @Override
    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * Method to make all geese target this.
     * @param geese List of entities to target this.
     */
    public void distract(List<Goose> geese) {
        for (Goose goose : geese) {
            goose.changeTarget(this);
        }
    }
}
