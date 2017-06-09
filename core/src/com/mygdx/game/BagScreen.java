package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BagScreen implements Screen {

	private GameSlagyom game;
	protected Stage stage;
	private Viewport viewport;
	private OrthographicCamera camera;

	private Texture background;
	private Sprite backgroundSprite;

	private Texture selectionBackground;
	private Sprite selectionBackgroundSprite;
	boolean selection;

	private Label name;
	private Drawable toolDraw; 
	private ImageButton toolIcon;
	private TextButton[] tools;
	private TextButton use;
	private TextButton exit;
	
	public BagScreen(final GameSlagyom game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new FitViewport(640, 480);
		// viewport = new ScreenViewport(camera);
		selection = false;
		viewport.apply();

		background = new Texture("res/bag/bagBackground.png");
		backgroundSprite = new Sprite(background);

		selectionBackground = new Texture("res/bag/bagSelectionBG.png");
		selectionBackgroundSprite = new Sprite(selectionBackground);

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		stage = new Stage(viewport, game.batch);

		// Create Table
		Table mainTable = new Table();
		mainTable.setLayoutEnabled(false);
		mainTable.setFillParent(true);
		mainTable.top();
		
		Table optionTable = new Table();
		optionTable.setLayoutEnabled(false);
		optionTable.setFillParent(true);
		optionTable.top();



		name = new Label("Tools", MenuScreen.skin);
		toolDraw = new TextureRegionDrawable(new TextureRegion(new Texture("res/bag/bluePotion.png")));
		toolIcon = new ImageButton(toolDraw);
		tools = new TextButton[5];
		use = new TextButton("Use", MenuScreen.skin);
		exit = new TextButton("Return", MenuScreen.skin);
		tools[0] = new TextButton("Blue potion", MenuScreen.skin);
		tools[1] = new TextButton("Red potion", MenuScreen.skin);


		tools[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				/*selection = true;
				use.setVisible(true);
				exit.setVisible(true);*/
				showInfo();
			}
		});

		tools[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selection = true;
			}
		});

		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				/*selection = false;
				use.setVisible(false);
				exit.setVisible(false);*/
				hideInfo();
			}
		});

		// Add buttons to table

		name.setPosition(65, 425);
		mainTable.add(name);
		tools[0].setPosition(250, 420);
		tools[1].setPosition(250, 370);
		toolIcon.setPosition(41, 43);
		
		mainTable.add(tools[0]);
		mainTable.add(tools[1]);
		mainTable.add(toolIcon);
		
		use.setPosition(468, 98);
		exit.setPosition(468, 48);
		optionTable.add(use);
		optionTable.add(exit);
		
		if (selection) {
			use.setVisible(true);
			exit.setVisible(true);
			toolIcon.setVisible(true);
		} else if (!selection) {
			use.setVisible(false);
			exit.setVisible(false);
			toolIcon.setVisible(false);
		}

		stage.addActor(mainTable);
		stage.addActor(optionTable);
		
		
	}

	
	private void showInfo (){
		selection = true;
		use.setVisible(true);
		exit.setVisible(true);
		toolIcon.setVisible(true);
	}
	
	private void hideInfo () {
		selection = false;
		use.setVisible(false);
		exit.setVisible(false);
		toolIcon.setVisible(false);
		
	}
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		if (selection)
			selectionBackgroundSprite.draw(game.batch);
		else
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
		MenuScreen.skin.dispose();
		MenuScreen.atlas.dispose();
	}

}
