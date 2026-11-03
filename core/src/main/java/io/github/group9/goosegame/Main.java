package io.github.group9.goosegame;

import com.badlogic.gdx.Game;
import io.github.group9.goosegame.screens.Gamescreen;

/** javadoc. */
public class Main extends Game {

  @Override
  public void create() {
    setScreen(new Gamescreen());
  }

  @Override
  public void dispose() {
    if (getScreen() != null) {
      getScreen().dispose();
    }
    super.dispose();
  }
}
