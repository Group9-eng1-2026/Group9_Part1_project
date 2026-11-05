package io.github.group9.goosegame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.GeeseUtilities.Pathfinder;
import io.github.group9.goosegame.World;
import io.github.group9.goosegame.GeeseUtilities.GeeseSpawner;

public class GooseTest {
    @Test
    public void TestNotTelefrag(){
        Player player = new Player(1, 1);
        World world = new World();
        GeeseSpawner.spawnGoose(player, world);
        Goose goose = world.geese.getFirst();
        assertNotEquals(player.pos, goose.pos, "Goose spawned atop player");
    }

    //Pathfinder hard to test due to random nature
}
