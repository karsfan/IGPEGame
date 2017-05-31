package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameSlagyom.State;

public class PauseScreen implements Screen {

	private GameSlagyom game;
	protected Stage stage;
	private Viewport viewport;
	private OrthographicCamera camera;

	private Texture background;
	private Sprite backgroundSprite;

	public PauseScreen(final GameSlagyom game) {
		this.game = game;

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(500, 500, camera);
		viewport.apply();

		background = new Texture("res/background.png");
		backgroundSprite = new Sprite(background);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		stage = new Stage(viewport, game.batch);

		// Create Table
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.top();
		Label pauseLabel = new Label("PAUSE", MenuScreen.skin);
		// Create buttons
		TextButton saveGame = new TextButton("Save game", MenuScreen.skin);
		TextButton loadGame = new TextButton("Load game", MenuScreen.skin);
		TextButton returnButton = new TextButton("Return", MenuScreen.skin);
		TextButton exitButton = new TextButton("Exit", MenuScreen.skin);

		final Drawable noDialog = null;

		// Add listeners to buttons
		saveGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameSlagyom.saveGame();
				game.setScreen(GameSlagyom.playScreen);
				PlayScreen.drawDialog("Game saved!");

			}
		});

		loadGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameSlagyom.loadGame();
				//System.out.println("CARICATO");
				game.setScreen(GameSlagyom.playScreen);
				PlayScreen.drawDialog("Game loaded!");


			}
		});

		returnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(GameSlagyom.playScreen);
			}
		});

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		// Add buttons to table
		mainTable.add(pauseLabel).pad(30);
		mainTable.row();
		mainTable.add(saveGame).padTop(Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 5);
		mainTable.row();
		mainTable.add(loadGame).pad(5);
		mainTable.row();
		mainTable.add(returnButton).pad(5);
		mainTable.row();
		mainTable.add(exitButton).pad(5);
		mainTable.row();

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

		stage.act();
		stage.draw();
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(game.playScreen);

		}

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
		MenuScreen.skin.dispose();
		MenuScreen.atlas.dispose();
	}

}