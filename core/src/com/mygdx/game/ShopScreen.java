package it.slagyom;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import it.slagyom.src.Map.Item;
import it.slagyom.src.Map.Item.Level;
import it.slagyom.src.Map.StaticObject.Element;
import it.slagyom.src.World.Game;

public class ShopScreen implements Screen {
	private enum Category {
		POTIONS, WEAPONS, PARCHMENTS
	}

	public Category currentCategory;

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
	private Table parchmentsTable;

	private Table optionsTable;
	private TextButton buy;
	private TextButton delete;
	private TextButton exit;
	private Label coins;

	public Item itemSelected;
	TextButton[] potions;

	public ShopScreen(final GameSlagyom game) {
		this.game = game;
		itemSelected = new Item();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(854, 480, camera);
		//viewport = new ScreenViewport(camera);
		//viewport = new StretchViewport(640, 480, camera);
		// viewport = new FitViewport(640, 480, camera);
		selection = false;
		viewport.apply();
		background = new Texture("res/shop/shopBackground.png");
		backgroundSprite = new Sprite(background);

		selectionBackground = new Texture("res/shop/shopSelectionBG.png");
		selectionBackgroundSprite = new Sprite(selectionBackground);

		stage = new Stage(viewport, game.batch);

		currentCategory = Category.POTIONS;

		// OPTIONS TABLE
		optionsTable = new Table();
		optionsTable.setLayoutEnabled(false);
		optionsTable.setFillParent(true);
		optionsTable.top();
		buy = new TextButton("Buy", MenuScreen.skin);
		delete = new TextButton("Delete", MenuScreen.skin);
		exit = new TextButton("Return", MenuScreen.skin);

		delete.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Game.character.bag.removeItem(itemSelected.getElement(), itemSelected.getLevel());
				potions[0].setText("Blue potion    x" + Game.character.bag.getNumberOf(Element.POTION, Level.FIRST));
				potions[1].setText("Red potion    x" + Game.character.bag.getNumberOf(Element.POTION, Level.SECOND));
				potions[2].setText("Green potion  x" + Game.character.bag.getNumberOf(Element.POTION, Level.THIRD));
			}
		});

		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.emptyShopIcon);
				hideInfo();
			}
		});

		LoadingImage.emptyShopIcon.setPosition(41, 43);
		LoadingImage.rightArrow.setPosition(183, 274);
		LoadingImage.leftArrow.setPosition(15, 274);
		coins = new Label("" + Game.character.coins, MenuScreen.skin);
		coins.setPosition(99, 274);
		LoadingImage.emptyShopIcon.setVisible(true);

		buy.setPosition(473, 110);
		buy.setVisible(false);
		delete.setPosition(473, 70);
		delete.setVisible(false);
		exit.setPosition(473, 30);
		exit.setVisible(false);

		optionsTable.add(LoadingImage.emptyShopIcon);
		optionsTable.add(LoadingImage.rightArrow);
		optionsTable.add(LoadingImage.leftArrow);
		optionsTable.add(LoadingImage.emptyShopIcon);

		optionsTable.add(buy);
		optionsTable.add(delete);
		optionsTable.add(coins);
		optionsTable.add(exit);

		LoadingImage.rightArrow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (currentCategory == Category.POTIONS)
					currentCategory = Category.WEAPONS;
				else if (currentCategory == Category.WEAPONS)
					currentCategory = Category.PARCHMENTS;
				else if (currentCategory == Category.PARCHMENTS)
					currentCategory = Category.POTIONS;
			}
		});

		LoadingImage.leftArrow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (currentCategory == Category.POTIONS)
					currentCategory = Category.PARCHMENTS;
				else if (currentCategory == Category.WEAPONS)
					currentCategory = Category.POTIONS;
				else if (currentCategory == Category.PARCHMENTS)
					currentCategory = Category.WEAPONS;
			}
		});
		// END OPTIONS TABLE

		// POTIONS TABLE
		potionsTable = new Table();
		Label potionsLabel;

		potionsTable.setLayoutEnabled(false);

		potionsLabel = new Label("Potions", MenuScreen.skin);
		potions = new TextButton[3];
		potions[0] = new TextButton(
				"Blue potion    $"/*
									 * + Game.character.bag.getNumberOf(Element.
									 * POTION, Level.FIRST)
									 */, MenuScreen.skin);
		potions[1] = new TextButton(
				"Red potion    $" /*
									 * + Game.character.bag.getNumberOf(Element.
									 * POTION, Level.SECOND)
									 */, MenuScreen.skin);
		potions[2] = new TextButton(
				"Green potion  $" /*
									 * + Game.character.bag.getNumberOf(Element.
									 * POTION, Level.THIRD)
									 */ , MenuScreen.skin);

		potions[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.bluePotion);
				itemSelected.setElement(Element.POTION);
				itemSelected.setLevel(Level.FIRST);
			}
		});

		potions[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.redPotion);
				itemSelected.setElement(Element.POTION);
				itemSelected.setLevel(Level.SECOND);
			}
		});

		potions[2].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.greenPotion);
				itemSelected.setElement(Element.POTION);
				itemSelected.setLevel(Level.THIRD);
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

		weaponsLabel = new Label("Weapons", MenuScreen.skin);
		weapons = new TextButton[3];
		weapons[0] = new TextButton("Ascia", MenuScreen.skin);
		weapons[1] = new TextButton("Spada", MenuScreen.skin);
		weapons[2] = new TextButton("Mazza", MenuScreen.skin);

		weapons[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.spear);
			}
		});

		weapons[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.sword);
			}
		});

		weapons[2].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.spear);
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

		// PARCHMENTS TABLE
		parchmentsTable = new Table();
		Label parchmentsLabel;
		TextButton[] parchments;

		parchmentsTable.setVisible(false);
		parchmentsTable.setLayoutEnabled(false);

		parchmentsLabel = new Label("Parchments", MenuScreen.skin);
		parchments = new TextButton[3];
		parchments[0] = new TextButton("Parchment1", MenuScreen.skin);
		parchments[1] = new TextButton("Parchment2", MenuScreen.skin);
		parchments[2] = new TextButton("Parchment3", MenuScreen.skin);

		parchments[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.bluePotion);
			}
		});

		parchments[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.sword);
			}
		});

		parchments[2].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showInfo(LoadingImage.spear);
			}
		});

		parchmentsLabel.setPosition(20, 425);
		parchmentsTable.add(parchmentsLabel);

		parchments[0].setPosition(250, 420);
		parchmentsTable.add(parchments[0]);

		parchments[1].setPosition(250, 370);
		parchmentsTable.add(parchments[1]);

		parchments[2].setPosition(250, 320);
		parchmentsTable.add(parchments[2]);
		// END PARCHMENTS TABLE

		stage.addActor(potionsTable);
		stage.addActor(weaponsTable);
		stage.addActor(parchmentsTable);
		stage.addActor(optionsTable);
	}

	private void showInfo(ImageButton icon) {
		icon.setPosition(41, 43);
		optionsTable.removeActor(icon);
		optionsTable.add(icon);
		LoadingImage.emptyShopIcon.setVisible(false);

		selection = true;
		buy.setVisible(true);
		delete.setVisible(true);
		exit.setVisible(true);
	}

	private void hideInfo() {
		LoadingImage.emptyShopIcon.setVisible(true);

		selection = false;
		buy.setVisible(false);
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

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.swapScreen(GameSlagyom.State.PLAYING);
			Game.world.semaphore.release();
		}

		if (currentCategory == Category.POTIONS) {
			potionsTable.setVisible(true);
			weaponsTable.setVisible(false);
			parchmentsTable.setVisible(false);
		} else if (currentCategory == Category.WEAPONS) {
			weaponsTable.setVisible(true);
			potionsTable.setVisible(false);
			parchmentsTable.setVisible(false);
		} else if (currentCategory == Category.PARCHMENTS) {
			parchmentsTable.setVisible(true);
			potionsTable.setVisible(false);
			weaponsTable.setVisible(false);
		}
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
=======
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
	}

	@Override
	public void resize(int width, int height) {
<<<<<<< HEAD
		if (height < 705){
			width = 1253;
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
		viewport.update(width, height);
		//stage.getViewport().setScreenSize(width, height);
		//camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
=======
		// viewport.update(width, height);
<<<<<<< HEAD
<<<<<<< HEAD
		stage.getViewport().setWorldWidth(width);
		stage.getViewport().setWorldHeight(height);
=======
		stage.getViewport().setScreenSize(width, height);
		//stage.getViewport().setWorldWidth(width);
		//stage.getViewport().setWorldHeight(height);
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
=======
		stage.getViewport().setScreenSize(width, height);
		//stage.getViewport().setWorldWidth(width);
		//stage.getViewport().setWorldHeight(height);
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9
		// camera.viewportHeight = height;
		// camera.viewportWidth = width;
		// backgroundSprite.setSize(width, height);
		// camera.position.set(camera.viewportWidth / 2, camera.viewportHeight /
		// 2, 0);
>>>>>>> 1c31d2ebb2e096e3488d8a839652beffce0bf899
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
