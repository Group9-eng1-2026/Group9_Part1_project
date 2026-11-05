package io.github.group9.goosegame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.group9.goosegame.Entities.Goose;
import io.github.group9.goosegame.Entities.Player;


public class RenderScreen {
    // Screen dimensions and grid
    private final float WIDTH = 1280f;
    private final float HEIGHT = 720f;
    private final float GRID = 100f;

    // Drawing tools
    private final ShapeRenderer shapes = new ShapeRenderer();
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final OrthographicCamera camera = new OrthographicCamera();

    // Camera and viewport setup
    private final FitViewport viewport = new FitViewport(WIDTH, HEIGHT, camera);
    private final Matrix4 hudProjection = new Matrix4().setToOrtho2D(0, 0, WIDTH, HEIGHT);

    public RenderScreen() {
        font.getData().setScale(2f); // Font size
        font.setColor(1, 1, 1, 1);
    }

    public void render(World world, Clock clock, GameState state) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        viewport.apply();

        // Camera follows the player and stops at map edges
        Player player = world.getPlayer();
        camera.position.set(
            MathUtils.clamp(player.pos.x, WIDTH / 2f, World.WIDTH - WIDTH / 2f),
            MathUtils.clamp(player.pos.y, HEIGHT / 2f, World.HEIGHT - HEIGHT / 2f),
            0);
        camera.update();

        shapes.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(hudProjection);

        drawWorld(world);
        drawUI(clock, state);
    }


    private void drawWorld(World world) {
        Player p = world.getPlayer();

        // Renders map background (currently green rectangle)
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0.2f, 0.5f, 0.2f, 1f);
        shapes.rect(0,0, World.WIDTH, World.HEIGHT);
        shapes.end();

        // Temporary grid lines to help see movement, can be removed later
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(0.4f, 0.7f, 0.4f, 1f);
        for (float x = 0; x <= World.WIDTH; x += GRID) shapes.line(x, 0, x, World.HEIGHT);
        for (float y = 0; y <= World.HEIGHT; y += GRID) shapes.line(0, y, World.WIDTH, y);
        shapes.end();

        // Renders player (currently black rectangle)
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0, 0, 0, 1);
        shapes.rect(p.pos.x - Player.SIZE / 2f, p.pos.y - Player.SIZE / 2f, Player.SIZE, Player.SIZE);
        shapes.end();

        //Renders Geese
        for (Goose goose : world.geese) {
            shapes.begin(ShapeRenderer.ShapeType.Filled);
            shapes.setColor(255, 255, 255, 1);
            shapes.rect(goose.pos.x - Goose.SIZE / 2f, goose.pos.y - Goose.SIZE / 2f, Goose.SIZE, Goose.SIZE);
            shapes.end();
        }
    }

    // Renders UI elements (time left, pause and game over messages)
    private void drawUI(Clock clock, GameState state) {
        batch.begin();
        font.draw(batch, "Time Left: " + clock.formatTime(), 10, HEIGHT - 10);
        font.draw(batch, "Game State: " + state.toString(), 10, HEIGHT - 40); // Temporary display of Game State

        if (state == GameState.PAUSED) {
            font.draw(batch, "GAME PAUSED\nPress SPACE to start/resume", WIDTH / 2f, HEIGHT / 2f, 0, Align.center, false);
        }

        /* Display game over message when the game has ended, can be changed when win/lose conditions are implemented */
        if (state == GameState.ENDED) {
            font.draw(batch, "GAME OVER\nPress R to restart", WIDTH / 2f, HEIGHT / 2f, 0, Align.center, false);
        }
        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public void dispose() {
        shapes.dispose();
        batch.dispose();
        font.dispose();
    }
}
