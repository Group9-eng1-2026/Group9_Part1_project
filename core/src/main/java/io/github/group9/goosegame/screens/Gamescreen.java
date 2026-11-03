package io.github.group9.goosegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.group9.goosegame.controllers.Inputcontroller;
import io.github.group9.goosegame.entities.Entity;
import io.github.group9.goosegame.entities.Player;
import java.util.ArrayList;
import java.util.List;

/** javadoc. */
public class Gamescreen extends ScreenAdapter {
  private final OrthographicCamera camera;
  private final ShapeRenderer shapes;
  private final Player player;
  private final List<Entity> entities = new ArrayList<>();

  /** javadoc. */
  public Gamescreen() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    shapes = new ShapeRenderer();

    player = new Player(0, 0, 30, 30);
    Gdx.input.setInputProcessor(new Inputcontroller(player));
    entities.add(player);

    // Test entity
    entities.add(new Entity(300, 300, 60, 60) {});
  }

  @Override
  public void render(float delta) {
    // update
    player.updatePosition(delta, entities);

    // draw
    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    camera.update();
    shapes.setProjectionMatrix(camera.combined);

    shapes.begin(ShapeRenderer.ShapeType.Filled);
    for (Entity e : entities) {
      shapes.setColor(e instanceof Player ? Color.YELLOW : Color.GRAY);
      shapes.rect(e.getPosX(), e.getPosY(), e.getSizeX(), e.getSizeY());
    }
    shapes.end();
  }

  @Override
  public void resize(int width, int height) {
    camera.setToOrtho(false, width, height);
  }

  @Override
  public void dispose() {
    shapes.dispose();
  }
}
