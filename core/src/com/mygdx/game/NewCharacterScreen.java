package com.mygdx.game;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class NewCharacterScreen implements Screen {
	
	private GameSlagyom game;
	protected Stage stage;
	private Viewport viewport;
	private OrthographicCamera camera;

	private Texture background;
	private Sprite backgroundSprite;

	private TextureAtlas atlas;
	protected Skin skin;
	MenuScreen menuscreen;


	public NewCharacterScreen (final GameSlagyom game, final MenuScreen menuscreen) {
		this.game = game;
		this.menuscreen = menuscreen;
		atlas = new TextureAtlas("menu/vhs/vhs-ui.atlas");
		skin = new Skin(Gdx.files.internal("menu/vhs/vhs-ui.json"), atlas);

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(500, 500, camera);
		viewport.apply();
		
		background = new Texture("res/background.png");
		backgroundSprite = new Sprite(background);

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		stage = new Stage(viewport, game.batch);
		Gdx.input.setInputProcessor(stage);

		// Create Table
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.top();

		// Create buttons

		TextField name = new TextField("Name", skin);
		
		TextButton mButton = new TextButton("M", skin);
		TextButton fButton = new TextButton("F", skin);


		//TextButton returnButton = new TextButton("Return", skin);
		// Add listeners to buttons
		/*defaultLevelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
				menuscreen.music.stop();
			}
		});*/

	
		// Add buttons to table
		mainTable.add(name).pad(5).padTop(Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 4);
		mainTable.row();
		mainTable.add(mButton).pad(5); 
		mainTable.add(fButton).pad(5); 

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