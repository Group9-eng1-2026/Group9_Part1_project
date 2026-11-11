package io.github.group9.goosegame.Events;

import io.github.group9.goosegame.Entities.EventEntity;
import io.github.group9.goosegame.Items.Inventory;
import io.github.group9.goosegame.Items.Item;
import io.github.group9.goosegame.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reload extends HiddenEvent{

    public Reload(float x, float y) {
        super(new EventEntity(x, y));
    }

    @Override
    public void activate(World world) {
        this.triggered = true;
        Inventory inv = world.getPlayer().getInventory();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (inv.getItem(i) != null) {
                items.add(inv.getItem(i));
            }
        }
        Random random = new Random();
        Item toAdd = items.get(random.nextInt(items.size()));
        world.getPlayer().getInventory().add(toAdd, 1);
    }
}
