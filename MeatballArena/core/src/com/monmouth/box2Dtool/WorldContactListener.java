package com.monmouth.box2Dtool;


import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.monmouth.screens.GameScreen;

public class WorldContactListener {
private GameScreen screen;
private Array<Body> eliminatedEnemy = new Array<Body>(); 
private Array<Body> lostLife = new Array<Body>();
public WorldContactListener(GameScreen screen) {
    this.screen = screen;
}
}
