package io.github.group9.goosegame.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.GeeseUtilities.Pathfinder;

import java.util.LinkedList;
import java.util.Queue;

import static com.badlogic.gdx.math.MathUtils.random;

public class Goose extends Entity{
    //Constants
    public static final float SIZE = 32f;
    public static final float SPEED = 75f;

    //Attributes
    public Queue<Vector2> moves = new LinkedList<>();
    private Player player;
    public int recovering = 0;

    //Constructor
    public Goose(float x, float y, Player player){
        super(x, y, SIZE, SPEED);
        this.player = player;
    }

    //Methods
    /** Takes a move from gooses pathfinder that will navigate it
     * towards the player
     * @param deltaTime Time since last frame
     * @param dx Additional offset
     * @param dy ADditional offse
     * @param worldWidth Used to constrain Movement
     * @param worldHeight Used to constrain Movement
     */
    @Override
    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        if (recovering > 0)
        {
            recovering--;
            this.moves.clear();
        }
        if (moves.isEmpty()) {
            Pathfinder.generateMoves(player, this);
        }

        Vector2 direction = moves.poll();

        // Maintains constant speed in all directions
        if (direction.len2() > 0) {
            direction.nor();
        }

        if (random.nextBoolean()) { dx *= -1; }
        if (random.nextBoolean()) { dy *= -1; }
        pos.x += dx;
        pos.y += dy;

        pos.mulAdd(direction, SPEED * deltaTime);


        // Keeps Goose within world boundaries
        float half = SIZE / 2f;
        pos.x = MathUtils.clamp(pos.x, half, worldWidth - half);
        pos.y = MathUtils.clamp(pos.y, half, worldHeight - half);
    }
}
