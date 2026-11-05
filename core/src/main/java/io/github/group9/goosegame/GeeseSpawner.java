package io.github.group9.goosegame;

import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import java.util.Random;

public class GeeseSpawner {
    private static int gooseCount = 0;
    private static Random random = new Random();

    public static void spawnGoose(Player player, World world) {

        float spawnX = player.pos.x + 5f + random.nextFloat() * 5f;
        float spawnY = player.pos.y + 5f + random.nextFloat() * 5f;
        world.geese.add(new Goose(spawnX, spawnY, player));
        gooseCount++;
    }

    public static int getGooseCount() { return gooseCount; }
}
