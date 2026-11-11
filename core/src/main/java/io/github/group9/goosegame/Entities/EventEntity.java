package io.github.group9.goosegame.Entities;

public class EventEntity extends Entity {
    public static final float SIZE = 20f;

    public EventEntity(float x, float y) {
        super(x, y, SIZE, 0f);
    }

    @Override
    public void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight) {
        throw new UnsupportedOperationException("Not to be supported.");
    }
}
