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

public class HealthUI extends Sprite
{
	private TextureAtlas textureAtlas;
    private Animation health;
    private Array<TextureAtlas.AtlasRegion> hpFrames;
    private TextureRegion hp;
    public String hpNumber = "5hp";
    public int amountOfHP = 6;
    
	public HealthUI()
	{
		textureAtlas = new TextureAtlas(Gdx.files.internal("Health/HealthDisplay.atlas"));
    	hpFrames = textureAtlas.findRegions(getHP());
    	health = new Animation(1f, hpFrames);
        setBounds(0, 0, hpFrames.get(0).getRegionWidth(), hpFrames.get(0).getRegionHeight());    	
        setRegion(hpFrames.get(0));
	}
	
	public String getHP()
	{
		amountOfHP = amountOfHP - 1;
		
		if(amountOfHP == 5)
		{
			hpNumber = "5hp";
		}
		
		else if(amountOfHP == 4)
		{
			hpNumber = "4hp";
		}
		
		else if(amountOfHP == 3)
		{
			hpNumber = "3hp";
		}
		
		else if(amountOfHP == 2)
		{
			hpNumber = "2hp";
		}
		
		else
		{
			hpNumber = "1hp";
		}
		
		return hpNumber;
	}
	
	public void update(float elapsedTime) 
	{
		setRegion(health.getKeyFrame(elapsedTime, true));
	}
}