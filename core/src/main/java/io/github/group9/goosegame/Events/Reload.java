package io.github.group9.goosegame.Events;

import io.github.group9.goosegame.Entities.EventEntity;
import io.github.group9.goosegame.Items.Bread;
import io.github.group9.goosegame.Items.EnergyDrink;
import io.github.group9.goosegame.Items.Inventory;
import io.github.group9.goosegame.Items.Item;
import io.github.group9.goosegame.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reload extends HiddenEvent{
    private Random random = new Random();
    private static Class[] items = {Bread.class, EnergyDrink.class};
    public Reload(float x, float y) {
        super(new EventEntity(x, y));
    }

    @Override
    public void activate(World world) {
        this.triggered = true;
        Inventory inv = world.getPlayer().getInventory();
        int itemNum = random.nextInt(items.length);
        Class<?> itemToAdd = items[itemNum];
        try{
            Object toAdd = itemToAdd.getDeclaredConstructor().newInstance();
            world.getPlayer().getInventory().add((Item) toAdd, 1);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
