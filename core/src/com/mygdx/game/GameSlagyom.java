package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameSlagyom extends Game {

	public static enum State {
		MENU, NEWGAME, CONTINUEGAME, OPTIONMENU, PLAYING, BATTLE, PAUSE, WELCOME
	};

	private static State currentState;

	static MenuScreen menuScreen;
	static NewCharacterScreen newCharacterScreen;
	static InitializerScreen initializerScreen;
	static OptionScreen optionScreen;
	static BattleScreen battlescreen;
	static PlayScreen playScreen;

	SpriteBatch batch;

	public GameSlagyom() {
		
	}

	@Override
	public void create() {
		
		batch = new SpriteBatch();
		new LoadingImage();
		menuScreen = new MenuScreen(this);
		newCharacterScreen = new NewCharacterScreen(this);
		initializerScreen = new InitializerScreen(this);
		optionScreen = new OptionScreen(this);
		currentState = State.MENU;
		setScreen(menuScreen);
		
		//setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
	
	public static void setState(State newState){
		currentState = newState;
	}
	
	public  void swapScreen(State newState){
		setState(newState);
		if(currentState == State.MENU){
			setScreen(menuScreen);
			Gdx.input.setInputProcessor(menuScreen.stage);
		}
		else if(currentState == State.PLAYING){
			playScreen = new PlayScreen(this, NewCharacterScreen.charName);
			setScreen(playScreen);
			menuScreen.music.stop();
		}
		else if(currentState == State.OPTIONMENU){
			setScreen(optionScreen);
			Gdx.input.setInputProcessor(optionScreen.stage);
		}
		else if(currentState == State.NEWGAME){
			setScreen(newCharacterScreen);
			Gdx.input.setInputProcessor(newCharacterScreen.stage);
		}
		else if(currentState == State.WELCOME){
			setScreen(initializerScreen);
			Gdx.input.setInputProcessor(initializerScreen.stage);
		}
		else if(currentState == State.BATTLE){
			battlescreen = new BattleScreen(this);
			setScreen(battlescreen);
		}
		else if(currentState == State.PAUSE){
			setScreen(menuScreen);
			Gdx.input.setInputProcessor(menuScreen.stage);
		}
		else if(currentState == State.CONTINUEGAME){
			playScreen = new PlayScreen(this, "");
			setScreen(playScreen);		
		}
	}
}
