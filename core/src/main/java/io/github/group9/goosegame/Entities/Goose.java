package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.GeeseUtilities.Pathfinder;

import java.util.LinkedList;
import java.util.Queue;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Class used to represent Geese and handle their movement
 */
public class Goose extends Entity{
    //Constants
    public static final float SIZE = 32f;
    public static final float SPEED = 150f;

    //Attributes
    public Queue<Vector2> moves = new LinkedList<>();
    private Entity target;
    public float recovering = 0;

    //Constructor
    public Goose(float x, float y, Player player){
        super(x, y, SIZE, SPEED);
        this.target = player;
    }

    //Methods
    /**
     * Takes a move from gooses pathfinder that will navigate it
     * towards the player
     * @param deltaTime Time since last frame - To make speed constant with regard to to frame rate
     * @param dx Additional offset to help randomise movement
     * @param dy Additional offset to help randomise movement
     * @param worldWidth Used to constrain Movement to map
     * @param worldHeight Used to constrain Movement to map
     */
    @Override
    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        if (recovering > 0)
        {
            recovering -= deltaTime;
            this.moves.clear();
        }
        if (moves.isEmpty()) {
            Pathfinder.generateMoves(target, this);
        }

        Vector2 direction = moves.poll();

        // Maintains constant speed in all directions
        if (direction.len2() > 0) {
            direction.nor();
        }

        if (random.nextBoolean()) { dx *= -1; }
        if (random.nextBoolean()) { dy *= -1; }
        direction.x += dx;
        direction.y += dy;

        pos.mulAdd(direction, SPEED * deltaTime);


        // Keeps Goose within world boundaries
        float half = SIZE / 2f;
        pos.x = MathUtils.clamp(pos.x, half, worldWidth - half);
        pos.y = MathUtils.clamp(pos.y, half, worldHeight - half);
    }

    /**
     * Used to change pathfinding target of Geese.
     * @param target Entity for Goose to target
     */
    public void changeTarget(Entity target){
        this.target = target;
    }
}
