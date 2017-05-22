package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameSlagyom extends Game{
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//setScreen(new MenuScreen(this));
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
}
