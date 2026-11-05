package io.github.group9.goosegame;

import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Entity;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import java.util.Random;

public class Pathfinder {
    /** Used so move generation can become Asynchronous to every 5 frames.
     *
     * @param target Target.
     * @param goose Subject TO generate moves for.
     */
    public static void generateMoves(Entity target, Goose goose){
        if (goose.recovering > 0) {
            for (int i = 1; i <= goose.recovering; i++) {
                goose.moves.add(new Vector2(0, 0));
            }
        }
        Random random = new Random();
        float deltaX = (target.pos.x - goose.pos.x)/Math.abs(target.pos.x-goose.pos.x);
        float deltaY = (target.pos.y - goose.pos.y)/Math.abs(target.pos.y-goose.pos.y);

        float distractedChance = 0.07f;
        for (int i = 0; i < 50; i++){
            if (!(random.nextFloat() > distractedChance)) { deltaX *= -1; }
            if (!(random.nextFloat() > distractedChance)) { deltaY *= -1; }
            goose.moves.add(new Vector2(deltaX, deltaY));
        }
    }
}
