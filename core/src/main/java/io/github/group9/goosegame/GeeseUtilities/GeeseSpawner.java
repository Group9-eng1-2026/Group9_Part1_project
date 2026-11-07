package io.github.group9.goosegame.GeeseUtilities;

import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

import java.util.Random;

/**
 * Class that handles the spawning of geese
 */
public class GeeseSpawner {
    private static int gooseCount = 0;
    private static Random random = new Random();

    /** Static method used to create new Geese.
     *
     * @param player Used for players Position to avoid spawning geese directly atop player.
     * @param world Used to access list of goose instances.
     */
    public static void spawnGoose(Player player, World world) {
        float dx = 50f + random.nextFloat() * 5f;
        float dy = 50f + random.nextFloat() * 5f;
        if (random.nextBoolean()) { dx *= -1; }
        if (random.nextBoolean()) { dy *= -1; }
        float spawnX = player.pos.x + dx * 10;
        float spawnY = player.pos.y + dy * 10;
        world.geese.add(new Goose(spawnX, spawnY, player));
        gooseCount++;
    }

    public static int getGooseCount() { return gooseCount; }
}
