package io.github.group9.goosegame.Entities;



import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Vector2;



public abstract class Entity {
    public float entitySize;
    private float entitySpeed;

    public final Vector2 pos;

    public Entity(float x, float y, float entitySize, float entitySpeed) {
        pos = new Vector2(x, y);
        this.entitySize = entitySize;
        this.entitySpeed = entitySpeed;
    }
    public abstract void update(float deltaTime, float dx, float dy, float worldWidth, float worldHeight);

    public boolean intersects(Entity other){
        Rectangle thisHitbox = new Rectangle(pos.x, pos.y, this.entitySize, this.entitySize);
        Rectangle otherHitbox = new Rectangle(other.pos.x, other.pos.y, other.entitySize, other.entitySize);
        return thisHitbox.overlaps(otherHitbox);
    }
}

