package io.github.group9.goosegame;

import java.util.Collections;

import io.github.group9.goosegame.entities.Entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {
    private static class TestEntity extends Entity {
        public TestEntity(float posX, float posY, float sizeX, float sizeY) {
            super(posX, posY, sizeX, sizeY);
        }

        public TestEntity(float posX, float posY, float sizeX, float sizeY, float deltaX, float deltaY) {
            super(posX, posY, sizeX, sizeY, deltaX, deltaY);
        }
    }

    @Test
    void moveCorrectlyUpdatesPosition(){
        Entity e1 = new TestEntity(10f, 10f, 5f, 5f, 2f, -3f);

        //Test with standard delta time value
        e1.move(1f, Collections.emptyList());

        //Test move method
        assertEquals(12f, e1.getPosX(), 0.001f, "X value was not as expected in standard move");
        assertEquals(7f, e1.getPosY(), 0.001f, "Y value was not as expected in standard move");
    }

    @Test
    void moveWithNoVelocity(){
        Entity e1 = new TestEntity(10f, 10f, 5f, 5f);

        e1.move(1f, Collections.emptyList());

        //Test when no velocity
        assertEquals(10f, e1.getPosX(), 0.001f, "X value not as expected when velocity is not provided");
        assertEquals(10f, e1.getPosY(), 0.001f, "Y value not as expected when velocity is not provided");
    }

    @Test
    void testDeltaTime(){
        Entity e1 = new TestEntity(10f, 10f, 5f, 5f, 2f, -2f);

        e1.move(0.5f, Collections.emptyList());

        assertEquals(11f, e1.getPosX(), 0.001f, "X value not as expected when provided delta time");
        assertEquals(9f, e1.getPosY(), 0.001f, "Y value not as expected when provided delta time");
    }

}
