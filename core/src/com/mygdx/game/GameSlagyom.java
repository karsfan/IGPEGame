package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameSlagyom extends Game {

	public static enum State {
		MENU, NEWGAME, CONTINUEGAME, OPTIONMENU, PLAYING, BATTLE, PAUSE, WELCOME, BAG
	};

	private static State currentState;

	static MenuScreen menuScreen;
	static NewCharacterScreen newCharacterScreen;
	static InitializerScreen initializerScreen;
	static OptionScreen optionScreen;
	static BattleScreen battlescreen;
	static PauseScreen pauseScreen;
	static PlayScreen playScreen;
	static BagScreen bagScreen;

	public static Preferences prefs;
	SpriteBatch batch;

	public GameSlagyom() {
	}

	@Override
	public void create() {

		batch = new SpriteBatch();
		new LoadingImage();
		menuScreen = new MenuScreen(this);
		optionScreen = new OptionScreen(this);
		currentState = State.MENU;
		pauseScreen = new PauseScreen(this);
		setScreen(menuScreen);

		prefs = Gdx.app.getPreferences("My saved game");

	}

	@SuppressWarnings("static-access")
	public static void saveGame() {
		prefs.putString("map", com.mygdx.game.src.World.Game.world.getMap().getMapPath());
		prefs.putString("name", com.mygdx.game.src.World.Game.character.name);
		prefs.putFloat("xCharPosition", com.mygdx.game.src.World.Game.character.x);
		prefs.putFloat("yCharPosition", com.mygdx.game.src.World.Game.character.y);
		prefs.putFloat("health", com.mygdx.game.src.World.Game.character.health);
		prefs.putFloat("power", com.mygdx.game.src.World.Game.character.power);
		prefs.putInteger("coins", com.mygdx.game.src.World.Game.character.coins);

		prefs.flush();

	}

	@SuppressWarnings("deprecation")
	public void loadGame() {
		prefs = Gdx.app.getPreferences("My saved game");

		if (currentState == State.PAUSE)
			com.mygdx.game.src.World.Game.thread.stop();

		playScreen = new PlayScreen(this, prefs.getString("map"), prefs.getString("name"));

		com.mygdx.game.src.World.Game.character.x = prefs.getFloat("xCharPosition");
		com.mygdx.game.src.World.Game.character.y = prefs.getFloat("yCharPosition");
		com.mygdx.game.src.World.Game.character.health = prefs.getFloat("health");
		com.mygdx.game.src.World.Game.character.power = prefs.getFloat("power");
		com.mygdx.game.src.World.Game.character.coins = prefs.getInteger("coins");

	}

	@Override
	public void render() {
		super.render();
	}

	public static void setState(State newState) {
		currentState = newState;
	}

	public void swapScreen(State newState) {

		setState(newState);

		if (currentState == State.MENU) {
			setScreen(menuScreen);
			Gdx.input.setInputProcessor(menuScreen.stage);
		} else if (currentState == State.PLAYING) {
			setScreen(playScreen);
			menuScreen.music.stop();
			Gdx.input.setInputProcessor(null);
		} else if (currentState == State.OPTIONMENU) {
			setScreen(optionScreen);
			Gdx.input.setInputProcessor(optionScreen.stage);
		} else if (currentState == State.NEWGAME) {
			newCharacterScreen = new NewCharacterScreen(this);
			setScreen(newCharacterScreen);
			Gdx.input.setInputProcessor(newCharacterScreen.stage);
		} else if (currentState == State.WELCOME) {
			initializerScreen = new InitializerScreen(this);
			setScreen(initializerScreen);
			Gdx.input.setInputProcessor(initializerScreen.stage);
		} else if (currentState == State.BATTLE) {
			battlescreen = new BattleScreen(this);
			setScreen(battlescreen);
		} else if (currentState == State.PAUSE) {
			setScreen(pauseScreen);
			Gdx.input.setInputProcessor(pauseScreen.stage);
		} else if (currentState == State.BAG) {
			bagScreen = new BagScreen(this);
			setScreen(bagScreen);
			Gdx.input.setInputProcessor(bagScreen.stage);
		} else if (currentState == State.CONTINUEGAME) {
			setScreen(playScreen);
			Gdx.input.setInputProcessor(null);
		}
	}
}
