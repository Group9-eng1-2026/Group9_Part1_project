package io.github.group9.goosegame.Entities;

import io.github.group9.goosegame.World;
import io.github.group9.goosegame.Entities.Player;

public abstract class Item {
    public final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() { 
        return name; 
    }

    public abstract boolean use(Player player, World world);
}