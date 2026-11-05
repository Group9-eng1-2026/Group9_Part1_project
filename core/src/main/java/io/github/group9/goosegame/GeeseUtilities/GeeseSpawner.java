package io.github.group9.goosegame.GeeseUtilities;

import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

import java.util.Random;

public class GeeseSpawner {
    private static int gooseCount = 0;
    private static Random random = new Random();

    public static void spawnGoose(Player player, World world) {
        float dx = random.nextFloat() * 5f;
        float dy = random.nextFloat() * 5f;
        if (random.nextBoolean()) { dx *= -1; }
        if (random.nextBoolean()) { dy *= -1; }
        float spawnX = player.pos.x + dx * 100;
        float spawnY = player.pos.y + dy * 100;
        world.geese.add(new Goose(spawnX, spawnY, player));
        gooseCount++;
    }

    public static int getGooseCount() { return gooseCount; }
}
