




package com.monmouth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.monmouth.game.MeatballArena;

public class GameOverScreen implements Screen {
	private Viewport viewport;
    private Stage stage;
	final MeatballArena game;
    Label GameOver;
    Label goBack;
    public GameOverScreen(MeatballArena game) {
        this.game = game;
        this.viewport = new FitViewport(MeatballArena.V_WIDTH,MeatballArena.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(this.viewport,game.batch);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("blackchance.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 20;
        BitmapFont font = generator.generateFont(parameter);
        
        //will add a picture for a main character dying here.
        GameOver = new Label("Game Over. You reached", new Label.LabelStyle(font,Color.BLACK));
        GameOver.setPosition(250, 200);
        goBack = new Label("Click anywhere to go back to start screen", new Label.LabelStyle(font, Color.BLACK));
        goBack.setPosition(250, 100);
        stage.addActor(GameOver);
        stage.addActor(goBack);
     
    }


	
	@Override
	public void show() {
		 
	}
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.justTouched()) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
        stage.draw();
        game.batch.begin();
        
        game.batch.end();
    }
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }
}
