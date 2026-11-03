package io.github.group9.goosegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private GameLoop gameLoop;

    @Override
    public void create() {
        gameLoop = new GameLoop();
    }

    @Override
    public void render() {
        gameLoop.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        gameLoop.resize(width, height);
    }

    @Override
    public void dispose() {
        gameLoop.dispose();
    }
}
