package io.github.group9.goosegame;

import com.badlogic.gdx.math.Vector2;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import java.util.Random;

public class Pathfinder {
    /** Used so move generation can become Asynchronous to every 5 frames.
     *
     * @param player Target.
     * @param goose Subject.
     */
    public static void generateMoves(Player player, Goose goose){
        Random random = new Random();
        float deltaX = (player.pos.x - goose.pos.x)/Math.abs(player.pos.x-goose.pos.x);
        float deltaY = (player.pos.y - goose.pos.y)/Math.abs(player.pos.y-goose.pos.y);

        for (int i = 0; i < 50; i++){
            goose.moves.add(new Vector2(deltaX, deltaY));
        }
    }
}
