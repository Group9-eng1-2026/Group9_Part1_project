package io.github.group9.goosegame.Events;

import com.badlogic.gdx.scenes.scene2d.Event;
import io.github.group9.goosegame.Entities.Player;
import io.github.group9.goosegame.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Class to handle the spawning and triggering of events
 */
public class EventManager {
    //Constants
    public static final Class[] EXISTING_EVENTS = {Reload.class};
    //Attributes
    public List<HiddenEvent> events;
    private int eventsTriggered;
    private Random random = new Random();
    //Constructor
    public EventManager () {
        events = new ArrayList<>();
        eventsTriggered = 0;
    }
    //Methods
    public int getEventsTriggered(){
        return eventsTriggered;
    }

    /**
     * For all existing events checks if they should be activated
     * @param world World events operate in.
     */
    private void checkEvents(World world) {
        for (HiddenEvent event : events) {
            if (event.checkTrigger(world.getPlayer())) {
                event.activate(world);
                eventsTriggered++;
            }
        }
    }

    public void update(World world){
        //Spawn events
        if (events.size() < 5) {
            float x = random.nextFloat(World.WIDTH);
            float y = random.nextFloat(World.HEIGHT);
            int eventNum = random.nextInt(EventManager.EXISTING_EVENTS.length);
            Class<?> classToAdd = EXISTING_EVENTS[eventNum];
            try {
                Object toAdd = classToAdd.getDeclaredConstructor(float.class, float.class).newInstance(x, y);
                HiddenEvent eventToAdd = (HiddenEvent) toAdd;
                events.add(eventToAdd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Check if any events need to be triggered
        checkEvents(world);
        //Remove used events
        events.removeIf(event -> event.triggered);
    }
}
