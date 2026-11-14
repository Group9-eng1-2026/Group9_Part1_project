package io.github.group9.goosegame;

import io.github.group9.goosegame.Entities.*;
import io.github.group9.goosegame.Events.EventManager;
import io.github.group9.goosegame.Events.HiddenEvent;
import io.github.group9.goosegame.Events.Reload;
import io.github.group9.goosegame.Items.Bread;
import io.github.group9.goosegame.Items.EnergyDrink;
import io.github.group9.goosegame.Items.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Texture;

import io.github.group9.goosegame.Items.Item;

public class RenderScreen {
    // Screen dimensions and grid
    private final float WIDTH = 1024f;
    private final float HEIGHT = 768f;
    private final float GRID = 100f;

    // Inventory dimensions
    private final float SLOT_SIZE = 64f;
    private final float GAP = 10f;

    // Drawing tools
    private final ShapeRenderer shapes = new ShapeRenderer();
    private final BitmapFont font = new BitmapFont();
    private final OrthographicCamera camera = new OrthographicCamera();
    private SpriteBatch batch = new SpriteBatch();

    // Camera and viewport setup
    private final FitViewport viewport = new FitViewport(WIDTH, HEIGHT, camera);
    private final Matrix4 hudProjection = new Matrix4().setToOrtho2D(0, 0, WIDTH, HEIGHT);

    // Textures and assets, including dimensions in pixels
    Texture mapTexture = new Texture(Gdx.files.internal("Map.png")); // 480x320
    Texture winText = new Texture(Gdx.files.internal("Win.png")); // 47x25
    Texture gameOver = new Texture(Gdx.files.internal("GameOver.png")); // 27x19

    Texture gooseTexture = new Texture(Gdx.files.internal("Goose.png")); // 18x27
    Texture breadTexture = new Texture(Gdx.files.internal("Bread.png")); // 24x24
    Texture drinkTexture = new Texture(Gdx.files.internal("Drink.png")); // 24x24

    public RenderScreen() {
        font.getData().setScale(2f); // Font size
        font.setColor(0, 0, 0, 1);
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

        drawWorld(world);
        drawInventory(player.getInventory());
        drawUI(clock, state, world.eventManager);
    }


    private void drawWorld(World world) {
        Player p = world.getPlayer();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0.52f, 0.78f, 0.41f, 1f);
        shapes.rect(0,0, World.WIDTH, World.HEIGHT);
        shapes.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(mapTexture, 0, 0, World.WIDTH, World.HEIGHT);
        batch.end();

        //Renders Items
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Entity itemEntity : world.itemEntities) {
            if (itemEntity instanceof BreadEntity) {
                float x = ((Entity) (BreadEntity) itemEntity).pos.x - BreadEntity.SIZE / 2f;
                float y = ((Entity) (BreadEntity) itemEntity).pos.y - BreadEntity.SIZE / 2f;
                batch.draw(breadTexture, x, y, BreadEntity.SIZE, BreadEntity.SIZE);
            }
        }
        batch.end();

        // Renders player (currently black rectangle)
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0, 0, 0, 1);
        shapes.rect(p.pos.x - Player.SIZE / 2f, p.pos.y - Player.SIZE / 2f, Player.SIZE, Player.SIZE);
        shapes.end();

        //Renders Geese
        batch.begin();
        for (Goose goose : world.geese) {
            batch.draw(gooseTexture, goose.pos.x - Goose.SIZE / 2f, goose.pos.y - Goose.SIZE / 2f, Goose.SIZE, Goose.SIZE * 27/18);
        }
        batch.end();

        //Renders events
        for (HiddenEvent i : world.eventManager.events) {
            if (i instanceof Reload) {
                shapes.begin(ShapeRenderer.ShapeType.Filled);
                shapes.setColor(255, 0, 0, 1);
                float x = i.entity.pos.x - EventEntity.SIZE / 2f;
                float y = i.entity.pos.y - EventEntity.SIZE / 2f;
                shapes.rect(x, y, EventEntity.SIZE, EventEntity.SIZE);
                shapes.end();
            }
        }
    }

    // Draws inventory hotbar, selected slot is blue
    private void drawInventory(Inventory inventory) {
        float totalWidth = Inventory.INV_SIZE * SLOT_SIZE + (Inventory.INV_SIZE - 1) * GAP;
        float startX = (WIDTH - totalWidth) / 2f;
        float y = 20f;
        shapes.setProjectionMatrix(hudProjection);

        // Inventory backgrounds,
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < Inventory.INV_SIZE; i++) {
            if (i == inventory.getSelectedSlot()) shapes.setColor(0.4f, 0.7f, 0.8f, 1f); // Makes selected slot blue
            else shapes.setColor(0.9f, 0.9f, 0.9f, 1f);
            shapes.rect(startX + i * (SLOT_SIZE + GAP), y, SLOT_SIZE, SLOT_SIZE);
        }
        shapes.end();

        // Inventory slot outlines
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(0.1f, 0.1f, 0.1f, 1f);
        for (int i = 0; i < Inventory.INV_SIZE; i++) {
            shapes.rect(startX + i * (SLOT_SIZE + GAP), y, SLOT_SIZE, SLOT_SIZE);
        }
        shapes.end();

        // Items and item counts
        batch.setProjectionMatrix(hudProjection);
        batch.begin();
        for (int i = 0; i < Inventory.INV_SIZE; i++) {
            float x = startX + i * (SLOT_SIZE + GAP);
            Item item = inventory.getItem(i);
            if (item != null) {
                if (item instanceof Bread) {
                    batch.draw(breadTexture, x + 8f, y + 8f, 48f, 48f);
                }
                if (item instanceof EnergyDrink) {
                    batch.draw(drinkTexture,  x + 8f, y + 8f, 48f, 48f);
                }
                if (inventory.getCount(i) > 1) {
                    font.draw(batch, String.valueOf(inventory.getCount(i)), x, y + SLOT_SIZE - 5f, SLOT_SIZE - 5f, Align.right, false);
                }
            }
        }
        batch.end();
    }

    // Renders UI elements (Time left, counters, pause and game over messages)
    private void drawUI(Clock clock, GameState state, EventManager eventManager) {
        batch.setProjectionMatrix(hudProjection);
        batch.begin();
        // Timer and counter displays
        font.draw(batch, "Time Left: " + clock.formatTime(), 10, HEIGHT - 10);
        font.draw(batch, "Item counter: " + Player.itemCount, 10, HEIGHT - 50);
        font.draw(batch, "Events Triggered: " + eventManager.getEventsTriggered(), 10, HEIGHT - 90);

        if (state == GameState.PAUSED) {
            font.draw(batch, "GAME PAUSED\nPress SPACE to start/resume", WIDTH / 2f, HEIGHT / 2f, 0, Align.center, false);
        }

        // Display game over message when game is lost
        if (state == GameState.LOSE) {
            batch.draw(gameOver, (WIDTH - gameOver.getWidth())/2, HEIGHT/2 + 25f);
            if (clock.timeLeft <= 0) {
            font.draw(batch, "You ran out of time\nPress R to restart", WIDTH / 2f, HEIGHT / 2f - 50f, 0, Align.center, false); 
            } else { 
                font.draw(batch, "The flock drained all your energy\nPress R to restart", WIDTH / 2f, HEIGHT / 2f - 50f, 0, Align.center, false); 
            }
        }

        if (state == GameState.WIN) {
            batch.draw(winText, (WIDTH - winText.getWidth()) / 2f , HEIGHT / 2f + 25f);
            // font.draw(batch, "YOU WIN!", WIDTH / 2f, HEIGHT / 2f + 50f, 0, Align.center, false);
            font.draw(batch, "Score: " + "\nItems Used: " + String.valueOf(Player.itemCount) + "\nEvents Triggered: " + "\nPress R to play again", WIDTH / 2f, HEIGHT / 2f - 25f, 0, Align.center, false);
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

        gooseTexture.dispose();
        winText.dispose();
        gameOver.dispose();
        breadTexture.dispose();
        drinkTexture.dispose();
        mapTexture.dispose();
    }
}
