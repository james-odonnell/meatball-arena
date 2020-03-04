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

public class Hammer extends Sprite
{
	private TextureAtlas hammer;
    private Animation animateIdleHammerLeft;
    private Animation animateSwingHammerLeft;
    private Animation animateIdleHammerRight;
    private Animation animateSwingHammerRight;
    private Array<TextureAtlas.AtlasRegion> idleFramesLeft;
    private Array<TextureAtlas.AtlasRegion> swingFramesLeft;
    private Array<TextureAtlas.AtlasRegion> idleFramesRight;
    private Array<TextureAtlas.AtlasRegion> swingFramesRight;
    //private TextureRegion hammerSwing;
    private boolean idle = true;
    boolean left = false;
    
	public Hammer()
	{
		hammer = new TextureAtlas(Gdx.files.internal("MainCharacter/Hammer.atlas"));
    	idleFramesLeft = hammer.findRegions("idleLeft");
    	swingFramesLeft = hammer.findRegions("swingLeft");
    	idleFramesRight = hammer.findRegions("idleRight");
    	swingFramesRight = hammer.findRegions("swingRight");
    	animateIdleHammerLeft = new Animation(1/17f, idleFramesLeft);
    	animateSwingHammerLeft = new Animation(1/7f, swingFramesLeft);
    	animateIdleHammerRight = new Animation(1/17f, idleFramesRight);
    	animateSwingHammerRight = new Animation(1/7f, swingFramesRight);
    	
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
	    			setRegion(animateIdleHammerLeft.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingHammerLeft.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	    	
	    	if (left == false)
	    	{
	    		if(idle==true)
	    		{
	    			setRegion(animateIdleHammerRight.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingHammerRight.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	 }
}

