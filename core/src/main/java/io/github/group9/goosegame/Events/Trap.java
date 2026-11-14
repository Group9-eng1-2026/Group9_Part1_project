package io.github.group9.goosegame.Events;

import io.github.group9.goosegame.Entities.EventEntity;
import io.github.group9.goosegame.GeeseUtilities.GeeseSpawner;
import io.github.group9.goosegame.World;

import java.util.Random;

public class Trap extends HiddenEvent {
    private static Random rand = new Random();
    public Trap(float x, float y) {
        super(new EventEntity(x, y));
    }

    @Override
    public void activate(World world) {
        this.triggered = true;
        for (int i = 0; i < rand.nextInt(3,5); i++) {
            GeeseSpawner.spawnGoose(world.getPlayer(), world);
        }
    }
}
