package com.monmouth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.monmouth.game.MeatballArena;
import com.monmouth.screens.GameScreen;

public class StoreScreen implements Screen{
	private Viewport viewport;
    private Stage stage;
    private MeatballArena game;
    private Texture imgHammer;
    GameScreen gamescreen;
    Label buyHealth;
    Label buyHammer;
    Label buyAxe;
    Label buySword;
    Label nextLevel;
    public StoreScreen(MeatballArena game) {
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
        buyHealth = new Label("Click here to restore health (costs 5 gold per heart).", new Label.LabelStyle(font, Color.BLACK));
        buyHealth.setPosition(175,400);
        buyHealth.setTouchable(Touchable.disabled); //all clickables are currently disabled and acting as placeholders.
        buyHealth.setBounds(175,400,buyHealth.getWidth(),buyHealth.getHeight());
        buyHealth.addListener(new ClickListener(){
            /* @Override
             public void clicked(InputEvent event, float x, float y) {
 
                dispose();
            }
            */
        });
        imgHammer = new Texture(Gdx.files.internal("hammerPicture.png"));
        imgHammer.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        buyHammer = new Label("Click here to purchase the Hammer (costs 20 gold).", new Label.LabelStyle(font, Color.BLACK));
        buyHammer.setPosition(175,300);
        buyHammer.setTouchable(Touchable.disabled);
        buyHammer.setBounds(175,300,buyHammer.getWidth(),buyHammer.getHeight());
        buyHammer.addListener(new ClickListener(){
            /* @Override
             public void clicked(InputEvent event, float x, float y) {

                dispose();
            }
            */
        });
        buyAxe = new Label("Click here to purchase the Axe (costs 20 gold).", new Label.LabelStyle(font, Color.BLACK));
        buyAxe.setPosition(175,200);
        buyAxe.setTouchable(Touchable.disabled);
        buyAxe.setBounds(175,200,buyAxe.getWidth(),buyAxe.getHeight());
        buyAxe.addListener(new ClickListener(){
            /* @Override
             public void clicked(InputEvent event, float x, float y) {

                dispose();
            }
            */
        });
        buySword = new Label("Click here to purchase the Sword (costs 20 gold).", new Label.LabelStyle(font,Color.BLACK));
        buySword.setPosition(175,100);
        buySword.setTouchable(Touchable.disabled);
        buySword.setBounds(175,100,buySword.getWidth(),buySword.getHeight());
        buySword.addListener(new ClickListener(){
            /* @Override
             public void clicked(InputEvent event, float x, float y) {

                dispose();
            }
            */
        });
        
        // since the store screen will show up during gameplay there will be an option to go to the next level.
        nextLevel = new Label("Click here to advance to the next level.", new Label.LabelStyle(font, Color.BLACK));
        nextLevel.setPosition(490, 5);
        nextLevel.setTouchable(Touchable.disabled);
        nextLevel.setBounds(490,5, nextLevel.getWidth(),nextLevel.getHeight());
        nextLevel.addListener(new ClickListener(){
            /* @Override
             public void clicked(InputEvent event, float x, float y) {

                dispose();
            }
            */
        });
        stage.addActor(buyHealth);
        stage.addActor(buyHammer);
        stage.addActor(buyAxe);
        stage.addActor(buySword);
        stage.addActor(nextLevel);
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
        game.batch.draw(imgHammer,75,300,60,60); // image of the hammer
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

