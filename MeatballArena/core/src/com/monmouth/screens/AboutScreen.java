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

public class AboutScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private MeatballArena game;
    Label whoDevelopedLabel;
    Label storyText;
    Label developers;
    Label goBack;
    public AboutScreen(MeatballArena game) {
        this.viewport = new FitViewport(MeatballArena.V_WIDTH, MeatballArena.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        this.game = game;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("blackchance.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 20;
        BitmapFont font = generator.generateFont(parameter);
        whoDevelopedLabel = new Label("Developers", new Label.LabelStyle(font, Color.BLACK));
        whoDevelopedLabel.setPosition(530, 450);
        developers = new Label("James O'Donnell\nNicolas Sciolaro\nAnthony Pinto", new Label.LabelStyle(font, Color.BLACK));
        developers.setPosition(500,350);
        storyText = new Label("It is a sad day in the Meatball Kingdom,\n their civilization is collapsing,\n their economy is ruined,\n and the kingdom is quickly spiraling into warfare. \n Use the latest Meatballian technology to eviscerate your foes\n and smash those opposing meatballs into meatcakes.\n  The fate of the kingdom is in your hands.", new Label.LabelStyle(font, Color.BLACK));
        storyText.setPosition(70,250);;
        goBack = new Label("Click anywhere to go back to start screen", new Label.LabelStyle(font, Color.BLACK));
        goBack.setPosition(35, 0);
        
        stage.addActor(whoDevelopedLabel);
        stage.addActor(storyText);
        stage.addActor(developers);
        stage.addActor(goBack);
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
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
    }
}