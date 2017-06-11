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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
	
	
	private Table weaponsTable;
	private Table potionsTable;
	
	private Table optionsTable;
	private TextButton use;
	private TextButton delete;
	private TextButton exit;

	public BagScreen(final GameSlagyom game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new FitViewport(640, 480);
		selection = false;
		viewport.apply();

		background = new Texture("res/bag/bagBackground.png");
		backgroundSprite = new Sprite(background);

		selectionBackground = new Texture("res/bag/bagSelectionBG.png");
		selectionBackgroundSprite = new Sprite(selectionBackground);

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		stage = new Stage(viewport, game.batch);

		// OPTIONS TABLE
		optionsTable = new Table();
		optionsTable.setLayoutEnabled(false);
		optionsTable.setFillParent(true);
		optionsTable.top();
		use = new TextButton("Use", MenuScreen.skin);
		delete = new TextButton("Delete", MenuScreen.skin);
		exit = new TextButton("Return", MenuScreen.skin);
		
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hideInfo();
			}
		});

		LoadingImage.emptyIcon.setPosition(41, 43);
		LoadingImage.arrow.setPosition(27, 214);

		LoadingImage.emptyIcon.setVisible(true);
		use.setPosition(473, 110);
		use.setVisible(false);
		delete.setPosition(473, 70);
		delete.setVisible(false);
		exit.setPosition(473, 30);
		exit.setVisible(false);

		optionsTable.add(LoadingImage.emptyIcon);
		optionsTable.add(LoadingImage.arrow);
		optionsTable.add(use);
		optionsTable.add(delete);
		optionsTable.add(exit);
		// END OPTIONS TABLE

		// POTIONS TABLE
		potionsTable = new Table();
		Label potionsLabel;
		TextButton[] potions;
		potionsTable.setLayoutEnabled(false);
		//potionsTable.setFillParent(true);
		//	potionsTable.top();

		potionsLabel = new Label("Potions", MenuScreen.skin);
		potions = new TextButton[3];
		potions[0] = new TextButton("Blue potion", MenuScreen.skin);
		potions[1] = new TextButton("Red potion", MenuScreen.skin);
		potions[2] = new TextButton("Green potion", MenuScreen.skin);

		potions[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.bluePotion, "Pozione blu");
			}
		});

		potions[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.redPotion, "Pozione rossa");
			}
		});

		potions[2].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.greenPotion, "Pozione verde");
			}
		});

		potionsLabel.setPosition(49, 425);
		potionsTable.add(potionsLabel);

		potions[0].setPosition(250, 420);
		potionsTable.add(potions[0]);

		potions[1].setPosition(250, 370);
		potionsTable.add(potions[1]);

		potions[2].setPosition(250, 320);
		potionsTable.add(potions[2]);
		// END POTIONS TABLE

		
		// WEAPON TABLE
		weaponsTable = new Table();
		Label weaponsLabel;
		TextButton[] weapons;
		
		weaponsTable.setVisible(false);
		weaponsTable.setLayoutEnabled(false);
	//	weaponsTable.setFillParent(true);
	//	weaponsTable.top();

		weaponsLabel = new Label("Weapons", MenuScreen.skin);
		weapons = new TextButton[3];
		weapons[0] = new TextButton("Ascia", MenuScreen.skin);
		weapons[1] = new TextButton("Cazzo", MenuScreen.skin);
		weapons[2] = new TextButton("Mazza", MenuScreen.skin);

		weapons[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.spear, "Spada");
			}
		});

		weapons[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.sword, "Pozione rossa");
			}
		});

		weapons[2].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.spear, "Pozione verde");
			}
		});

		weaponsLabel.setPosition(40, 425);
		weaponsTable.add(weaponsLabel);

		weapons[0].setPosition(250, 420);
		weaponsTable.add(weapons[0]);

		weapons[1].setPosition(250, 370);
		weaponsTable.add(weapons[1]);

		weapons[2].setPosition(250, 320);
		weaponsTable.add(weapons[2]);
		// END WEAPONS TABLE
		
		stage.addActor(potionsTable);
		stage.addActor(weaponsTable);
		stage.addActor(optionsTable);

	}

	private void showInfo(ImageButton icon, String string) {
		icon.setPosition(41, 43);
		optionsTable.add(LoadingImage.emptyIcon);
		optionsTable.removeActor(icon);
		optionsTable.add(icon);

		selection = true;
		use.setVisible(true);
		delete.setVisible(true);
		exit.setVisible(true);
	}

	private void hideInfo() {
		optionsTable.removeActor(LoadingImage.emptyIcon);
		optionsTable.add(LoadingImage.emptyIcon);

		selection = false;
		use.setVisible(false);
		delete.setVisible(false);
		exit.setVisible(false);
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

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			game.swapScreen(GameSlagyom.State.PLAYING);
		else if (Gdx.input.isKeyJustPressed(Keys.K)){
			hideInfo();
			potionsTable.setVisible(false);
			weaponsTable.setVisible(true);

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
