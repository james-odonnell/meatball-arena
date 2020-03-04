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

public class Axe extends Sprite
{
	private TextureAtlas axe;
    private Animation animateIdleAxeLeft;
    private Animation animateSwingAxeLeft;
    private Animation animateIdleAxeRight;
    private Animation animateSwingAxeRight;
    private Array<TextureAtlas.AtlasRegion> idleFramesLeft;
    private Array<TextureAtlas.AtlasRegion> swingFramesLeft;
    private Array<TextureAtlas.AtlasRegion> idleFramesRight;
    private Array<TextureAtlas.AtlasRegion> swingFramesRight;
    //private TextureRegion hammerSwing;
    private boolean idle = true;
    boolean left = false;
    
	public Axe()
	{
		axe = new TextureAtlas(Gdx.files.internal("MainCharacter/Axe.atlas"));
    	idleFramesLeft = axe.findRegions("idleLeft");
    	swingFramesLeft = axe.findRegions("swingLeft");
    	idleFramesRight = axe.findRegions("idleRight");
    	swingFramesRight = axe.findRegions("swingRight");
    	animateIdleAxeLeft = new Animation(1/17f, idleFramesLeft);
    	animateSwingAxeLeft = new Animation(1/7f, swingFramesLeft);
    	animateIdleAxeRight = new Animation(1/17f, idleFramesRight);
    	animateSwingAxeRight = new Animation(1/7f, swingFramesRight);
    	
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
	    			setRegion(animateIdleAxeLeft.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingAxeLeft.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	    	
	    	if (left == false)
	    	{
	    		if(idle==true)
	    		{
	    			setRegion(animateIdleAxeRight.getKeyFrame(elapsedTime, true));
	    		}
	    		
	    		else
	    		{
	    			setRegion(animateSwingAxeRight.getKeyFrame(1, true));
		    		idle = true;
	    		}
	    	}
	 }
}