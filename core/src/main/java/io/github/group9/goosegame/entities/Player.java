package io.github.group9.goosegame.entities;

import java.util.List;

/** The player-controlled character. */
public class Player extends Entity {
  boolean moveRight;
  boolean moveLeft;
  boolean moveUp;
  boolean moveDown;
  float dx = 0f;
  float dy = 0f;
  private static final float SPEED = 120f;

  /**
   * Boilerplate javadoc for now.
   *
   * @param posX Boilerplate javadoc for now.
   * @param posY Boilerplate javadoc for now.
   * @param sizeX Boilerplate javadoc for now.
   * @param sizeY Boilerplate javadoc for now.
   */
  public Player(float posX, float posY, float sizeX, float sizeY) {
    super(posX, posY, sizeX, sizeY);
  }

  /**
   * Boilerplate javadoc for now.
   *
   * @param delta Boilerplate javadoc for now.
   * @param entities Boilerplate javadoc for now.
   */
  public void updatePosition(float delta, List<Entity> entities) {
    if (moveRight) {
      dx += 1;
    }
    if (moveLeft) {
      dx -= 1;
    }
    if (moveUp) {
      dy += 1;
    }
    if (moveDown) {
      dy -= 1;
    }

    // diagonal movement
    if (dx != 0 && dy != 0) {
      float inv = (float) (1 / Math.sqrt(2));
      dx *= inv;
      dy *= inv;
    }
    changeVelocity(dx * SPEED, dy * SPEED);
    move(delta, entities);
  }

  /**
   * javadoc.
   *
   * @param moveRight Boilerplate javadoc for now.
   */
  public void setMoveRight(boolean moveRight) {
    this.moveRight = moveRight;
  }

  /**
   * javadoc.
   *
   * @param moveLeft Boilerplate javadoc for now.
   */
  public void setMoveLeft(boolean moveLeft) {
    this.moveLeft = moveLeft;
  }

  /**
   * javadoc.
   *
   * @param moveUp Boilerplate javadoc for now.
   */
  public void setMoveUp(boolean moveUp) {
    this.moveUp = moveUp;
  }

  /**
   * javadoc.
   *
   * @param moveDown Boilerplate javadoc for now.
   */
  public void setMoveDown(boolean moveDown) {
    this.moveDown = moveDown;
  }
}
