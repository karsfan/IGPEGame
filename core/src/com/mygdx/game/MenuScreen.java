package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.levels.editor.Editor;

public class MenuScreen implements Screen {
	static GameSlagyom game;
	private NewCharacterScreen newCharacterScreen; 
	private InitializerScreen initializerScreen;
	private OptionScreen optionScreen;
	protected Stage stage;
	private Viewport viewport;
	private OrthographicCamera camera;

	private Texture background;
	private Sprite backgroundSprite;
	public Music music;
	static TextureAtlas atlas;
	protected static Skin skin;
	public Table mainTable;

	public TextButton musicButton;
	public TextButton returnButton;
	
	public MenuScreen(final GameSlagyom game) {
		MenuScreen.game = game;
		atlas = new TextureAtlas("menu/vhs/vhs-ui.atlas");
		skin = new Skin(Gdx.files.internal("menu/vhs/vhs-ui.json"), atlas);

		music = Gdx.audio.newMusic(Gdx.files.internal("res/menuMusic.mp3"));
		//music.play();

		musicButton = new TextButton("Music", skin);
		returnButton = new TextButton("Return", skin);

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(500, 500, camera);
		viewport.apply();

		background = new Texture("res/background.png");
		backgroundSprite = new Sprite(background);

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		stage = new Stage(viewport, game.batch);
		optionScreen = new OptionScreen(game, MenuScreen.this);
		newCharacterScreen = new NewCharacterScreen(game, MenuScreen.this); 
		initializerScreen = new InitializerScreen(game, MenuScreen.this);
		// Stage should controll input:
		Gdx.input.setInputProcessor(stage);

		// Create Table
		mainTable = new Table();
		// Set table to fill stage
		mainTable.setFillParent(true);
		// Set alignment of contents in the table.
		mainTable.top();

		// Create buttons
		TextButton playButton = new TextButton("New Game", skin);
		TextButton continueButton = new TextButton("Continue game", skin);
		TextButton editorButton = new TextButton("Level editor", skin);
		TextButton optionsButton = new TextButton("Options", skin);
		TextButton exitButton = new TextButton("Exit", skin);
		
		// Add listeners to buttons
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game) Gdx.app.getApplicationListener()).setScreen(initializerScreen);
				((Game) Gdx.app.getApplicationListener()).setScreen(newCharacterScreen);
				Gdx.input.setInputProcessor(newCharacterScreen.stage);
			}
		});
		continueButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game, ""));
			}
		});
		editorButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new Editor();
			}
		});
		optionsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(optionScreen);
				Gdx.input.setInputProcessor(optionScreen.stage);
			}
		});
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		// Add buttons to table
		mainTable.add(playButton).pad(5).padTop(Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 5);
		mainTable.row();
		mainTable.add(continueButton).pad(5);
		mainTable.row();
		mainTable.add(editorButton).pad(5);
		mainTable.row();
		mainTable.add(optionsButton).pad(5);
		mainTable.row();
		mainTable.add(exitButton).pad(5);

		// Add table to stage
		stage.addActor(mainTable);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		backgroundSprite.draw(game.batch);
		game.batch.end();

		stage.act(delta);
		stage.draw();

		if (Gdx.input.isKeyPressed(Keys.SPACE))
			((Game) Gdx.app.getApplicationListener()).setScreen(initializerScreen);

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

		skin.dispose();
		atlas.dispose();
	}

}