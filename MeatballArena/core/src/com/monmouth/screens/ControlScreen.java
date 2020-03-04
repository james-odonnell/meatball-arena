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

public class ControlScreen implements Screen {
	private Viewport viewport;
    private Stage stage;
    private MeatballArena game;
    Label meatballMovement;
    Label mainWeapon;
   // Label secondaryWeapon;
    Label instructionPause;
    Label goBack;
    public ControlScreen(MeatballArena game) {
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
        meatballMovement = new Label("Use the\"Arrow keys \"to move!", new Label.LabelStyle(font, Color.BLACK));
        meatballMovement.setPosition(90,350);
        mainWeapon = new Label("Press \"A\" to use main weapon!", new Label.LabelStyle(font, Color.BLACK));
        mainWeapon.setPosition(90,300);
        instructionPause = new Label("Press \"P\" to pause the game!", new Label.LabelStyle(font,Color.BLACK));
        instructionPause.setPosition(90,200);
        goBack = new Label("Click anywhere to go back to start screen", new Label.LabelStyle(font, Color.BLACK));
        goBack.setPosition(35, 0);
        stage.addActor(meatballMovement);
        stage.addActor(instructionPause);
        stage.addActor(mainWeapon);
       // stage.addActor(secondaryWeapon);
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

