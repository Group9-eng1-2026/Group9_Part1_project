package io.github.group9.goosegame;

import java.util.List;

public abstract class Entity {
    private Position position;
    private Size size;
    private Velocity velocity;

    //Constructors
    public Entity(float posX, float posY, float sizeX, float sizeY) {
        this(posX, posY, sizeX, sizeY, 0f, 0f);
    }

    public Entity(float posX, float posY, float sizeX, float sizeY, float deltaX, float deltaY) {
        position = new Position(posX, posY);
        size = new Size(sizeX, sizeY);
        velocity = new Velocity(deltaX, deltaY);
    }

    //Methods
    public void move(float delta, List<Entity> entities) {
        //Delta is delta time from gdx to normalise movement speed to time
        float newX = this.position.getX() + this.velocity.getX() * delta;
        float newY = this.position.getY() + this.velocity.getY() * delta;
        //TODO use entities to do collision here
        this.position = new Position(newX, newY);
    }

    //Skeleton Method to fill in for collision checks
    public boolean intersects(Entity e) {
        throw new UnsupportedOperationException("intersects is not implemented");
    }

    public void changeVelocity(float deltaX, float deltaY){
        this.velocity = new Velocity(deltaX, deltaY);
    }

    public float getPosX() {
        return position.getX();
    }

    public float getPosY(){
        return position.getY();
    }

    public float getSizeX(){
        return size.getX();
    }

    public float getSizeY(){
        return size.getY();
    }

//Private helper classes.

    private static class Position {
        private final float posX;
        private final float posY;

        public float getX() {
            return posX;
        }

        public float getY() {
            return posY;
        }

        public Position(float posX, float posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    private static class Size {
        private final float sizeX;
        private final float sizeY;

        public float getX() {
            return sizeX;
        }

        public float getY() {
            return sizeY;
        }

        public Size(float sizeX, float sizeY) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }
    }

    private static class Velocity {
        private final float deltaX;
        private final float deltaY;

        public float getX() {
            return deltaX;
        }

        public float getY() {
            return deltaY;
        }

        public Velocity(float deltaX, float deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
