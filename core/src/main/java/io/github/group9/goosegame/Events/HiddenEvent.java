package io.github.group9.goosegame.Events;

import io.github.group9.goosegame.Entities.EventEntity;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

/**
 * Class to represent all events for polymorphism.
 */
public abstract class HiddenEvent {
    //Attributes
    public boolean triggered;
    public EventEntity entity;
    //Constructor
    public HiddenEvent(EventEntity e){
        triggered = false;
        entity = e;
    }
    //Methods
    /**
     * Method to check if event if colliding with player, this
     * tells to trigger.
     * @param player To check collisions with.
     * @return True -> Should trigger, False -> dont trigger.
     */
    public boolean checkTrigger(Player player) {
        return player.intersects(entity);
    }

    public abstract void activate(World world);
}
