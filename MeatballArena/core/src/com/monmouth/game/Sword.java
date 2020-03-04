package com.monmouth.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.*;

public class Sword extends Sprite
{
	private TextureAtlas sword;
    private Animation animateIdleSwordLeft;
    private Animation animateSwingSwordLeft;
    private Animation animateIdleSwordRight;
    private Animation animateSwingSwordRight;
    private Array<TextureAtlas.AtlasRegion> idleFramesLeft;
    private Array<TextureAtlas.AtlasRegion> swingFramesLeft;
    private Array<TextureAtlas.AtlasRegion> idleFramesRight;
    private Array<TextureAtlas.AtlasRegion> swingFramesRight;
    //private TextureRegion hammerSwing;
    private boolean idle = true;
    boolean left = false;
    
	public Sword()
	{
		sword = new TextureAtlas(Gdx.files.internal("MainCharacter/Sword.atlas"));
    	idleFramesLeft = sword.findRegions("idleSwordLeft");
    	swingFramesLeft = sword.findRegions("swingSwordLeft");
    	idleFramesRight = sword.findRegions("idleSwordRight");
    	swingFramesRight = sword.findRegions("swingSwordRight");
    	animateIdleSwordLeft = new Animation(1/17f, idleFramesLeft);
    	animateSwingSwordLeft = new Animation(1/7f, swingFramesLeft);
    	animateIdleSwordRight = new Animation(1/17f, idleFramesRight);
    	animateSwingSwordRight = new Animation(1/7f, swingFramesRight);
    	
    	setBounds(0, 0, idleFramesLeft.get(0).getRegionWidth(), idleFramesLeft.get(0).getRegionHeight());    	
        setRegion(idleFramesLeft.get(0)); // Using super.setRegion()
	}
	
	public void Idle()
	{
		idle = true;
	}
	
	public void Swing()
	{
		idle = false;
	}
	
	public void update(float elapsedTime) 
	{
	    	
	    	if(Gdx.input.isKeyPressed(Keys.LEFT)==true)
	    	{
	    		left = true;
	    	}
	    	
	    	else if(Gdx.input.isKeyPressed(Keys.RIGHT)==true)
	    	{
	    		left = false;
	    	}
	    	
	    	if (left == true)
	    	{
	    		if(idle==true)
	    		{
	    			setRegion(animateIdleSwordLeft.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingSwordLeft.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	    	
	    	if (left == false)
	    	{
	    		if(idle==true)
	    		{
	    			setRegion(animateIdleSwordRight.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingSwordRight.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	 }
}