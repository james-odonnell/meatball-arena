package com.monmouth.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Enemy2Right extends Sprite
{
	private TextureAtlas textureAtlas;
    private Animation animateMeatball;
    private Array<TextureAtlas.AtlasRegion> walkingFrames;
    private TextureRegion deadMeatball;
    private boolean alive;

    public Enemy2Right()
    {
    	textureAtlas = new TextureAtlas(Gdx.files.internal("Enemies/Enemy2WalkingRight.atlas"));
    	walkingFrames = textureAtlas.findRegions("walking");
    	animateMeatball = new Animation(1/17f, walkingFrames);

        deadMeatball = textureAtlas.findRegion("dead");

        setBounds(0, 0, walkingFrames.get(0).getRegionWidth(), walkingFrames.get(0).getRegionHeight());    	
        setRegion(walkingFrames.get(0)); // Using super.setRegion()
        alive = true;
    }
	

	public boolean isAlive() 
	{
    	return alive;
    }
	
	 public void kill() 
	 {
	    	alive = false;
	    	setColor(Color.ORANGE);
	 }
	 
	 public void update(float elapsedTime) 
	 {
	        // Set the next frame for the meatbalLSprite:
	    	if (alive) 
	    	{
	        	// Set meatballSprite's next frame from the animation:
	    		setRegion(animateMeatball.getKeyFrame(elapsedTime, true));
	    	}
	    	else 
	    	{
	        	setRegion(deadMeatball);
	    	}
	 }
}
