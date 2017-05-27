package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	public enum State { PLAYING, FIGHTING, PAUSE };
	
	static PlayScreen playScreen; 
	static BattleScreen battleScreen;

	public GameScreen (GameSlagyom game) {
		System.out.println("AFSI");
		playScreen = new PlayScreen(game, NewCharacterScreen.charName);
		battleScreen = new BattleScreen(game); 
	}

	@Override
	public void show() {
		((Game) Gdx.app.getApplicationListener()).setScreen(playScreen);
	}
	
	public static void swapScreen(State state) {
		if (state == State.FIGHTING)
			((Game) Gdx.app.getApplicationListener()).setScreen(battleScreen);
		else if (state == State.PAUSE)
			((Game) Gdx.app.getApplicationListener()).setScreen(battleScreen);
		else if (state == State.PLAYING)
			((Game) Gdx.app.getApplicationListener()).setScreen(playScreen);
		

	}

	
	
	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}