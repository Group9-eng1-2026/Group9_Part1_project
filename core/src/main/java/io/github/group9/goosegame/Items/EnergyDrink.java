package io.github.group9.goosegame.Items;

import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

public class EnergyDrink extends Item {
    private float duration = 3f;
    public EnergyDrink() {
        super("Energy Drink");
    }

    @Override
    public boolean use(Player player, World world) {
        if (player.getInventory().getSelectedCount() == 0) {
            return false;
        }
        if (player.faster) { return false; }
        player.faster = true;
        return true;
    }

    public boolean update(Player player, float delta) {
        if (duration <= 0) {
            player.faster = false; return true;
        }
        duration -= delta;
        return false;
    }
}
