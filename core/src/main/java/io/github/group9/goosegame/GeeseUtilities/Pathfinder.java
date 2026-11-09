package io.github.group9.goosegame.GeeseUtilities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Entity;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.World;

import java.util.Random;

public class Pathfinder {
    /**
     * Static method used to populate a gooses moves field.
     * At present simply does straight line pathfinding due to lack of walls.
     * @param target Target to pathfind to.
     * @param goose Subject to generate moves for.
     */
    public static void generateMoves(Entity target, Goose goose){
        /*If goose has touched another goose or player,
          halts temporarily and update reflects this.
        */
        if (goose.recovering > 0) {
            for (int i = 1; i <= goose.recovering; i++) {
                goose.moves.add(new Vector2(0, 0));
            }
        }

        //Straight line pathfinding to player
        Random random = new Random();
        float deltaX = Math.signum(target.pos.x - goose.pos.x);
        float deltaY = Math.signum(target.pos.y - goose.pos.y);

        /*Chance of distracting goose based off heuristic,
          this reduces clustering and makes geese more chaotic.
        */
        float distractedChance = 0.03f;
        for (int i = 0; i < 10; i++){
            if (!(random.nextFloat() > distractedChance)) { deltaX *= -1; }
            if (!(random.nextFloat() > distractedChance)) { deltaY *= -1; }
            goose.moves.add(new Vector2(deltaX, deltaY));
        }
    }
}
