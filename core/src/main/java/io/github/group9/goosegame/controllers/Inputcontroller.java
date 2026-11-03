package io.github.group9.goosegame.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.group9.goosegame.entities.Player;

/** Boilerplate javadoc for now. */
public class Inputcontroller extends InputAdapter {

  private final Player player;

  /**
   * Boilerplate javadoc for now.
   *
   * @param player Boilerplate javadoc for now.
   */
  public Inputcontroller(Player player) {
    this.player = player;
  }

  @Override
  public boolean keyDown(int keycode) {
    return setMovement(keycode, true);
  }

  @Override
  public boolean keyUp(int keycode) {
    return setMovement(keycode, false);
  }

  // called by both
  private boolean setMovement(int keycode, boolean pressed) {
    switch (keycode) {
      case Input.Keys.A:
        player.setMoveLeft(pressed);
        return true;
      case Input.Keys.D:
        player.setMoveRight(pressed);
        return true;
      case Input.Keys.W:
        player.setMoveUp(pressed);
        return true;
      case Input.Keys.S:
        player.setMoveDown(pressed);
        return true;
      default:
        return false;
    }
  }
}
