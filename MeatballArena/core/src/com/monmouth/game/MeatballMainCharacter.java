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
import com.badlogic.gdx.Input.Keys;

public class MeatballMainCharacter extends Sprite
{
	private TextureAtlas textureAtlas;
    private Animation animateMeatballRight;
    private Animation animateMeatballLeft;
    private Array<TextureAtlas.AtlasRegion> rightFrames;
    private Array<TextureAtlas.AtlasRegion> leftFrames;
    
	public MeatballMainCharacter()
	{
		textureAtlas = new TextureAtlas(Gdx.files.internal("MainCharacter/MeatballCharacter.atlas"));
    	rightFrames = textureAtlas.findRegions("right");
    	leftFrames = textureAtlas.findRegions("left");    
    	animateMeatballLeft = new Animation(1/17f, leftFrames);
    	animateMeatballRight = new Animation(1/17f, rightFrames);	
    	
        setBounds(0, 0, rightFrames.get(0).getRegionWidth(), rightFrames.get(0).getRegionHeight());    	
        setRegion(rightFrames.get(0)); // Using super.setRegion()
	}
	
	public void update(float elapsedTime) 
	 {
	        // Set the next frame for the meatbalLSprite:
	    	if (Gdx.input.isKeyPressed(Keys.LEFT)==true) 
	    	{
	        	// Set meatballSprite's next frame from the animation:
	    		setRegion(animateMeatballLeft.getKeyFrame(elapsedTime, true));
	    	}
	    	else if(Gdx.input.isKeyPressed(Keys.RIGHT)==true)
	    	{
	    		setRegion(animateMeatballRight.getKeyFrame(elapsedTime, true));
	    	}
	 }
}
