package com.monmouth.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.monmouth.game.MeatballArena;



public class StartScreen implements Screen {
    final MeatballArena game;
    private Viewport viewport;
    private Stage stage;
    private boolean changeToInstruction = false;
    GameScreen gamescreen;
    Label title;
    Label startLabel;
    Label controlsLabel;
    Label aboutLabel;
    Label quitLabel;
    Label testStore; // using to look at the store in the main menu, deactivated in final build
    Label testGameOver; //using to test the game over screen, deactivated in final build
    private Texture imgMeatball;
    private Sound startSound;
    public StartScreen(final MeatballArena game) {
        this.game = game;
        this.viewport = new FitViewport(MeatballArena.V_WIDTH, MeatballArena.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,255,255,1); //white background
        //Gdx.gl.glClearColor(0,0,0,1); // black background, need to make the meatball image transparent if we want to use this one.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	startSound = Gdx.audio.newSound(Gdx.files.internal("startGame.mp3"));

        stage.draw();
        game.batch.begin();
        game.batch.draw(imgMeatball,10,10,400,400);
        game.batch.end();
    }
    @Override
    public void show() {

    	
        imgMeatball = new Texture(Gdx.files.internal("meatball.png"));
        imgMeatball.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("blackchance.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 26;
        final BitmapFont font = generator.generateFont(parameter);
       
        title = new Label("Meatball Arena", new Label.LabelStyle(font, Color.RED)); //
        title.setSize(300,300);
        title.setPosition(250,300); 
      
        
        
        startLabel = new Label("Start Game", new Label.LabelStyle(font, Color.BLACK));
        startLabel.setPosition(500,300);
        startLabel.setTouchable(Touchable.enabled);
        startLabel.setBounds(500,300,startLabel.getWidth(),startLabel.getHeight());
        startLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
            	startSound.play();
            	//gamescreen.setGameState(State.RUN);
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        controlsLabel = new Label("Controls", new Label.LabelStyle(font, Color.BLACK));
        controlsLabel.setPosition(500, 250);
        controlsLabel.setTouchable(Touchable.enabled);
        controlsLabel.setBounds(500,250,controlsLabel.getWidth(),controlsLabel.getHeight());
        controlsLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	startSound.play();
                game.setScreen(new ControlScreen(game));
                dispose();
            }
        });
        aboutLabel = new Label("About", new Label.LabelStyle(font, Color.BLACK));
        aboutLabel.setPosition(500,200);
        aboutLabel.setTouchable(Touchable.enabled);
        aboutLabel.setBounds(500,200,aboutLabel.getWidth(),aboutLabel.getHeight());
        aboutLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	startSound.play();
                game.setScreen(new AboutScreen(game));
                dispose();
            }
        });
        quitLabel = new Label("Quit", new Label.LabelStyle(font, Color.BLACK)); 
        quitLabel.setPosition(500, 150);
        quitLabel.setTouchable(Touchable.enabled);
        quitLabel.setBounds(500,150,quitLabel.getWidth(),quitLabel.getHeight());
        quitLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        
       /*  testStore = new Label("Store (test)", new Label.LabelStyle(font, Color.BLACK));
        testStore.setPosition(500,100);
        testStore.setTouchable(Touchable.enabled);
        testStore.setBounds(500,100,testStore.getWidth(),testStore.getHeight());
        testStore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StoreScreen(game));
                dispose();
            }
        });
        
        testGameOver = new Label("game over scrub git gud lol", new Label.LabelStyle(font, Color.BLACK));
        testGameOver.setPosition(500,300);
        testGameOver.setTouchable(Touchable.enabled);
        testGameOver.setBounds(500,25,testGameOver.getWidth(),testGameOver.getHeight());
        testGameOver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
                game.setScreen(new GameOverScreen(game));
                dispose();
            } 
        });
        */
        stage.addActor(title);
        stage.addActor(startLabel);
        stage.addActor(controlsLabel);
        stage.addActor(aboutLabel);
        stage.addActor(quitLabel);
        //stage.addActor(testStore);
        //stage.addActor(testGameOver);
    }
    @Override
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
        startLabel.setTouchable(Touchable.disabled);
        controlsLabel.setTouchable(Touchable.disabled);
        aboutLabel.setTouchable(Touchable.disabled);
        quitLabel.setTouchable(Touchable.disabled);
    }
}