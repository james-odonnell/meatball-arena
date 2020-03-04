package com.monmouth.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.monmouth.game.MeatballArena;
import com.monmouth.game.MeatballEnemySprite;
import com.monmouth.game.MeatballEnemySpriteWalkingLeft;
import com.monmouth.game.MeatballMainCharacter;
import com.monmouth.game.Hammer;
import com.monmouth.game.HealthUI;
import com.monmouth.game.Sword;
import com.monmouth.game.Axe;
import com.monmouth.game.Enemy2Right;

public class GameScreen implements Screen
{
	final MeatballArena game;
	//Label pauseMenu;
	StartScreen StartScreen;
	StoreScreen StoreScreen;
    State state = State.RUN;
	private Texture texture;
    private OrthographicCamera gamecamera;
    private Viewport gameViewPort;
    private SpriteBatch batch;
	Texture background;
    private MeatballEnemySprite meatballEnemy;
    private MeatballEnemySpriteWalkingLeft enemyWalkingLeft;
    private Enemy2Right enemy2WalkingRight;
    private MeatballMainCharacter todd;
    private Hammer hammer;
    //private HealthUI healthUI;
    private Sword sword;
    private Axe axe;
    private float elapsedTime;
    BitmapFont font;
    private int money;
    private int health = 5;
    private int enemiesKilled = 0;
    private int currentLevel = 1;
    private Sound squish;
    Music BGM;
    //private int whichWeapon = 100;
    Weapon weapon = Weapon.HAMMER;

    boolean left = false;



    
	public OrthographicCamera getGamecamera() {
        return gamecamera;
    }
    public void setGamecamera(OrthographicCamera gamecamera) 
    {
        this.gamecamera = gamecamera;
    }
    //Box2D Variables
    //private World world;
    //private Box2DDebugRenderer box2DDR;



	//public GameScreen(MeatballArena meatballArenaGame) 

	
	public GameScreen(final MeatballArena game) 

	{

		

		this.game = game;
		batch = new SpriteBatch();
		todd = new MeatballMainCharacter();
		meatballEnemy = new MeatballEnemySprite();
		enemyWalkingLeft = new MeatballEnemySpriteWalkingLeft();
		enemy2WalkingRight = new Enemy2Right();
		hammer = new Hammer();
		//healthUI = new HealthUI();
		sword = new Sword();
		axe = new Axe();
		background = new Texture("arenabackground.jpg");
		meatballEnemy.setPosition(-350, 1);
		enemyWalkingLeft.setPosition(1600, 1);
		enemy2WalkingRight.setPosition(-1500, 1);
		todd.setPosition(-60, -60);
		sword.setPosition(100, -58);
		hammer.setPosition(100, -58);
		axe.setPosition(100, -58);
		BGM = Gdx.audio.newMusic(Gdx.files.internal("medievalMusic.mp3"));
		squish = Gdx.audio.newSound(Gdx.files.internal("SquishSound.wav"));
		BGM.setLooping(true);

        font = new BitmapFont();
        
	}
	@Override
	public void render(float delta) 
	{
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

        if (Gdx.input.isKeyJustPressed(Keys.P)) {
            if (state == State.RUN) {

                setGameState(State.PAUSE); // Pauses the game
              
            }
            else {
            	setGameState(State.RUN); // Pressing P while the game is paused will unpause the game.
            }
        }
		batch.begin();
		batch.draw(background, 0, 0);
		todd.draw(batch);
		axe.draw(batch);
		hammer.draw(batch);
        meatballEnemy.draw(batch);
        enemyWalkingLeft.draw(batch); 
        enemy2WalkingRight.draw(batch);
        //healthUI.draw(batch);
        sword.draw(batch);
        font.setColor(Color.RED);
        font.draw(batch, "Health Remaining: " + health, 1300, 800);
        font.draw(batch, "Gold Available: " + money, 1300, 730);
        font.draw(batch, "Level " + currentLevel, 20, 750);
        
        if (state.equals(State.PAUSE)){
        		font.draw(batch, "Paused. Press Q to return to the main menu.", 600, 450 );
        }
        
        if (state.equals(State.GAMEOVER)){
    		font.draw(batch, "Game over. You reached level " + currentLevel + "Press the spacebar to return to the main menu.", 600, 450 );
    }
        if (state.equals(State.NEXTLEVEL)){
        	font.draw(batch, "Level complete! Press the Space Bar to continue to the next level.", 600, 700);
        	//font.draw(batch, "Buy additional health or a new weapon between rounds! ", 600, 550);
        	
            font.draw(batch, "Press M to buy the Hammer. Costs 15 Gold.", 478, 452);
            font.draw(batch, "Press N to buy the Sword. Costs 50 gold.", 478, 364);
            font.draw(batch, "Press B to buy the Axe. Costs 30 gold.", 478, 276);
            //font.draw(batch, "Press V to buy a heart. Costs 60 Gold for 1 heart.", 478, 188);
            

        }
		batch.end();
		


		elapsedTime += Gdx.graphics.getDeltaTime();
        //runTime += 1;
        switch(state){
        case RUN:

        	switch(weapon){
        	
        	case HAMMER: // the switch for the hammer, which is used by default
        		axe.setScale(0); sword.setScale(0); hammer.setScale(1);
        		  if(Gdx.input.isKeyPressed(Keys.LEFT)) 
        	        {
        	        	hammer.setX(hammer.getX() - 200 * Gdx.graphics.getDeltaTime()); 
        	        	
        	        	if(left==false)
        	        	{
        	        		hammer.setX(hammer.getX()-363);
        	        	}
        	        	
        	        	left = true;
        	        }
        	        
        	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
        	        {
        	        	hammer.setX(hammer.getX() + 200 * Gdx.graphics.getDeltaTime()); 
        	        	
        	        	if(left==true)
        	        	{
        	        		hammer.setX(hammer.getX()+363);
        	        	}
        	        	
        	        	left = false;
        	        }
        	        

        	       
        	            if (enemiesKilled == currentLevel + 2){
        	            	currentLevel = currentLevel + 1; 
        	            	enemiesKilled = 0;
        	            	state = State.NEXTLEVEL;
        	            }
        	        if(Gdx.input.isKeyPressed(Keys.LEFT)) todd.setX(todd.getX() - 200 * Gdx.graphics.getDeltaTime());
        	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) todd.setX(todd.getX() + 200 * Gdx.graphics.getDeltaTime());
        	        
        	        if(Gdx.input.isKeyPressed(Keys.A)) 
        	        {
        	        	hammer.Swing();
        	        	
        	        	if(left == false)
          	        	{

          	        		if((todd.getX() + 345 - meatballEnemy.getX()) < -20 && (todd.getX() + 490 - meatballEnemy.getX()) > -20 ||
          	        				(todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20)
          	            	{
          	            		meatballEnemy.setPosition(5000, 50000);
          	            		money = money + 5;
          	            		enemiesKilled = enemiesKilled + 1;
          	            		health = health +1;
          	            		squish.play();
          	            	}
          	        		
          	        		else if(((todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20))
          	            	{
          	        			enemyWalkingLeft.setPosition(-5000, 50000);
          	            		money = money + 5;
          	            		enemiesKilled = enemiesKilled + 1;
          	            		health = health +1;
          	            		squish.play();
          	            	}
          	        	}
          	        	
          	        	else
          	        	{
          	        		if(((todd.getX() - meatballEnemy.getX())) - 0  > -20 && ((todd.getX() - meatballEnemy.getX()) - 145 ) < -20)
          	            	{
          	            		meatballEnemy.setPosition(5000, 50000);
          	            		money = money + 5;
          	            		enemiesKilled = enemiesKilled + 1;
          	            		health = health +1;
          	            		squish.play();
          	            	}
          	        		
          	        		else if(((todd.getX() - enemyWalkingLeft.getX())) - 0  > -20 && ((todd.getX() - enemyWalkingLeft.getX()) - 145 ) < -20 )
          	            	{
          	        			enemyWalkingLeft.setPosition(-5000, 50000);
          	            		money = money + 5;
          	            		enemiesKilled = enemiesKilled + 1;
          	            		health = health +1;
          	            		squish.play();
          	            	}
          	        	}
        	        }
        	        
        	        else {hammer.Idle();}
        	        break;
        		
        	
        	case SWORD: // the switch for the sword
        		axe.setScale(0); hammer.setScale(0); sword.setScale(1);
      		  if(Gdx.input.isKeyPressed(Keys.LEFT)) 
  	        {
  	        	sword.setX(sword.getX() - 200 * Gdx.graphics.getDeltaTime()); 
  	        	
  	        	if(left==false)
  	        	{
  	        		sword.setX(sword.getX()-363);
  	        	}
  	        	
  	        	left = true;
  	        }
  	        
  	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
  	        {
  	        	sword.setX(sword.getX() + 200 * Gdx.graphics.getDeltaTime()); 
  	        	
  	        	if(left==true)
  	        	{
  	        		sword.setX(sword.getX()+363);
  	        	}
  	        	
  	        	left = false;
  	        }
  	        

  	       
  	            if (enemiesKilled == currentLevel + 2){
  	            	currentLevel = currentLevel + 1; 
  	            	enemiesKilled = 0;
  	            	state = State.NEXTLEVEL;
  	            }
  	        if(Gdx.input.isKeyPressed(Keys.LEFT)) todd.setX(todd.getX() - 200 * Gdx.graphics.getDeltaTime());
  	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) todd.setX(todd.getX() + 200 * Gdx.graphics.getDeltaTime());
  	        
  	        if(Gdx.input.isKeyPressed(Keys.A)) 
  	        {
  	        	sword.Swing();
  	        	
  	        	if(left == false)
  	        	{

  	        		if((todd.getX() + 345 - meatballEnemy.getX()) < -20 && (todd.getX() + 490 - meatballEnemy.getX()) > -20 ||
  	        				(todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20)
  	            	{
  	            		meatballEnemy.setPosition(5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        		
  	        		else if(((todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20))
  	            	{
  	        			enemyWalkingLeft.setPosition(-5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        	}
  	        	
  	        	else
  	        	{
  	        		if(((todd.getX() - meatballEnemy.getX())) - 0  > -20 && ((todd.getX() - meatballEnemy.getX()) - 145 ) < -20)
  	            	{
  	            		meatballEnemy.setPosition(5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        		
  	        		else if(((todd.getX() - enemyWalkingLeft.getX())) - 0  > -20 && ((todd.getX() - enemyWalkingLeft.getX()) - 145 ) < -20 )
  	            	{
  	        			enemyWalkingLeft.setPosition(-5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        	}
  	        }
  	        
  	        else {sword.Idle();}
  	        break;
  		
        		
        	
        	
        	case AXE: // the switch for the axe
        		hammer.setScale(0); sword.setScale(0); axe.setScale(1);
      		  if(Gdx.input.isKeyPressed(Keys.LEFT)) 
  	        {
  	        	axe.setX(axe.getX() - 200 * Gdx.graphics.getDeltaTime()); 
  	        	
  	        	if(left==false)
  	        	{
  	        		axe.setX(axe.getX()-363);
  	        	}
  	        	
  	        	left = true;
  	        }
  	        
  	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
  	        {
  	        	axe.setX(axe.getX() + 200 * Gdx.graphics.getDeltaTime()); 
  	        	
  	        	if(left==true)
  	        	{
  	        		axe.setX(axe.getX()+363);
  	        	}
  	        	
  	        	left = false;
  	        }
  	        

  	       
  	            if (enemiesKilled == currentLevel + 2){
  	            	currentLevel = currentLevel + 1; 
  	            	enemiesKilled = 0;
  	            	state = State.NEXTLEVEL;
  	            }
  	        if(Gdx.input.isKeyPressed(Keys.LEFT)) todd.setX(todd.getX() - 200 * Gdx.graphics.getDeltaTime());
  	        if(Gdx.input.isKeyPressed(Keys.RIGHT)) todd.setX(todd.getX() + 200 * Gdx.graphics.getDeltaTime());
  	        
  	        if(Gdx.input.isKeyPressed(Keys.A)) 
  	        {
  	        	axe.Swing();
  	        	
  	        	if(left == false)
  	        	{

  	        		if((todd.getX() + 345 - meatballEnemy.getX()) < -20 && (todd.getX() + 490 - meatballEnemy.getX()) > -20 ||
  	        				(todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20)
  	            	{
  	            		meatballEnemy.setPosition(5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        		
  	        		else if(((todd.getX() + 345 - enemyWalkingLeft.getX()) < -20 && (todd.getX() + 490 - enemyWalkingLeft.getX()) > -20))
  	            	{
  	        			enemyWalkingLeft.setPosition(-1000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		health = health +1;
  	            		squish.play();
  	            	}
  	        	}
  	        	
  	        	else
  	        	{
  	        		if(((todd.getX() - meatballEnemy.getX())) - 0  > -20 && ((todd.getX() - meatballEnemy.getX()) - 145 ) < -20)
  	            	{
  	            		meatballEnemy.setPosition(5000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		squish.play();
  	            	}
  	        		
  	        		else if(((todd.getX() - enemyWalkingLeft.getX())) - 0  > -20 && ((todd.getX() - enemyWalkingLeft.getX()) - 145 ) < -20 )
  	            	{
  	        			enemyWalkingLeft.setPosition(-1000, 50000);
  	            		money = money + 5;
  	            		enemiesKilled = enemiesKilled + 1;
  	            		squish.play();
  	            	}
  	        	}
  	        }
  	        
  	        else {axe.Idle();}
  	        break;
  		
        	}

        if((todd.getX()) - meatballEnemy.getX()  < 10)
          {
          	meatballEnemy.setPosition(-350, 1);
          	//healthUI.getHP();
          	health = health - 1;
        	if (health == 0)
        	{ state = State.GAMEOVER; }
          	
          }	
        
        if((todd.getX()) - enemyWalkingLeft.getX()  > -300)
        {
        	enemyWalkingLeft.setPosition(1500, 1);
        	//healthUI.getHP();
        	health = health - 1;
        	if (health == 0)
        	{ state = State.GAMEOVER; }
        }
        
        if((todd.getX()) - enemy2WalkingRight.getX()  < 45)
        {
        	enemy2WalkingRight.setPosition(-1500, 1);
        	

        }

        if (meatballEnemy.isAlive()) 
        {
    		meatballEnemy.setX(meatballEnemy.getX() + currentLevel);
    		// If meatballEnemy leaves screen horizontally, it will come around again
    		if (meatballEnemy.getX() > 1450) 
    		{
    			meatballEnemy.setPosition(-350, 1);
    		}
    	}
    	else 
    	{
    		meatballEnemy.setY(meatballEnemy.getY() - 10);
    	}
        
        if (enemyWalkingLeft.isAlive()) 
        {
        	enemyWalkingLeft.setX(enemyWalkingLeft.getX() - currentLevel);
    		// If enemy leaves screen horizontally, it will come around again
    		if (enemyWalkingLeft.getX() < -150) 
    		{
    			enemyWalkingLeft.setPosition(1600, 1);
    		}
    	}
    	else 
    	{
    		enemyWalkingLeft.setY(enemyWalkingLeft.getY() - 10);
    	}

        if (enemy2WalkingRight.isAlive()) 
        {
        	enemy2WalkingRight.setX(enemy2WalkingRight.getX() + currentLevel);
    		// If meatballEnemy leaves screen horizontally, it will come around again
    		if (enemy2WalkingRight.getX() > 1450) 
    		{
    			enemy2WalkingRight.setPosition(-1500, 1);
    		}
    	}
    	else 
    	{
    		enemy2WalkingRight.setY(enemy2WalkingRight.getY() - 10);
    	}
        
        //healthUI.setX(1250);
        //healthUI.setY(690);     
        
        hammer.update(elapsedTime);
        sword.update(elapsedTime);
        axe.update(elapsedTime);
        todd.update(elapsedTime);
        meatballEnemy.update(elapsedTime);
        enemyWalkingLeft.update(elapsedTime);
        enemy2WalkingRight.update(elapsedTime);
        
        break;
        
        case NEXTLEVEL:
        	
    	
        	
        	if(Gdx.input.isKeyPressed(Keys.SPACE)){
        			state = State.RUN;
		       		left = false;
		       		meatballEnemy.setPosition(-200, 1);
		    		enemyWalkingLeft.setPosition(1500, 1);
		    		todd.setPosition(-80, -80);
		    		sword.setPosition(100, -58);
		    		hammer.setPosition(100, -58);
		    		axe.setPosition(100, -58);
        	}

        		if(Gdx.input.isKeyPressed(Keys.M) && money >= 15){
                   
        			money = money - 15;
                    weapon = Weapon.HAMMER;
                    state = State.RUN;
                }
                else if (Gdx.input.isKeyPressed(Keys.N) && money >=50)
                {          
                    money = money - 50;
                    weapon = Weapon.SWORD;
                    state = State.RUN;
                }
                if (Gdx.input.isKeyPressed(Keys.B) && money >= 30)
                {
                    money = money - 30;
                    weapon = Weapon.AXE;
                    state = State.RUN;
                
                 if (Gdx.input.isKeyPressed(Keys.V) && money >= 60)
                 {
                     /*if(healthUI.amountOfHP<5)
                     {
                    	 money = money - 60;
                    	 healthUI.amountOfHP = healthUI.amountOfHP + 2;
                    	 healthUI.getHP();
                     }
                     */
                	 money = money - 60;
                	 health = health + 1;
                	 
                     state = State.RUN;
                 }
                
                }
            
        	
        	
        case PAUSE:
        	 
        	 if(Gdx.input.isKeyPressed(Keys.Q)){
        		 state = State.RUN;
        		 BGM.stop();
        		 money = 0;
        		 currentLevel = 1;
        		 game.setScreen(new StartScreen(game));
        		 dispose();
        	
        	 }
        	 else
        	 {
        		 break;
        	}
        case GAMEOVER:
        	
       	 if(Gdx.input.isKeyPressed(Keys.SPACE)){
    		 state = State.RUN;
    		 BGM.stop();
    		 money = 0;
    		 currentLevel = 1;
    		 game.setScreen(new StartScreen(game));
    		 dispose();
    	
        }
        }
      
	}

	/*private void spawnMeatballEnemy()
	{
		MeatballEnemySprite meatballEnemy = new MeatballEnemySprite();
		meatballEnemy.setX(MathUtils.random(0, 800 - 64));
		meatballEnemy.setY(480);
		meatballEnemy.update(meatballEnemy);
		lastDropTime = TimeUtils.nanoTime();
		
		
	}
	*/
	@Override
	public void show() 
	{
		// start the playback of the background music
		// when the screen is shown
		BGM.play();
	}


	
	@Override
	public void dispose () 
	{
		batch.dispose();
		background.dispose();
	}

	@Override
	public void resize(int width, int height) 
	{
		
		
	}

	@Override
	public void pause() 
	{
		
		
	}

	@Override
	public void resume() 
	{
		
		
	}

	@Override
	public void hide() 
	{
		
		
	}
 
    public void setGameState(State s) {
        this.state = s;
    }
}
