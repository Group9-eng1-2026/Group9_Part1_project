package io.github.group9.goosegame.Items;

import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

public class EnergyDrink extends Item {
    private static float START_DURATION = 3f;
    private float duration;
    public EnergyDrink() {
        super("Energy Drink");;
    }

    @Override
    public boolean use(Player player, World world) {
        if (player.getInventory().getSelectedCount() == 0) {
            return false;
        }
        if (player.faster) { return false; }
        this.duration = START_DURATION;
        player.faster = true;
        player.exhaustion += 20;
        return true;
    }

    public boolean update(Player player, float delta) {
        if (this.duration <= 0) {
            player.faster = false; return true;
        }
        this.duration -= delta;
        return false;
    }
}
