package io.github.group9.goosegame;

import io.github.group9.goosegame.Entities.Entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private static class TestEntity extends Entity{
        public TestEntity(float x, float y, float size, float speed) {super(x, y, size, speed); }

        @Override
        public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    @Test
    public void TestEntityCollisionValid(){
        TestEntity entity = new TestEntity(1, 1, 1f, 0f);
        TestEntity other = new TestEntity(1, 1, 1f, 0f);

        assertTrue(entity.intersects(other));
    }

    @Test
    public void TestEntityCollisionInvalid(){
        TestEntity entity = new TestEntity(1, 1, 1f, 0f);
        TestEntity other = new TestEntity(1, 0, 1f, 0f);

        assertFalse(entity.intersects(other));
    }
}
