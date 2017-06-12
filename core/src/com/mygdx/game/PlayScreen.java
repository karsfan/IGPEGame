package com.mygdx.game;

import java.util.Iterator;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.Character.DynamicObjects;
import com.mygdx.game.src.Character.DynamicObjects.StateDynamicObject;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.StaticObject;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.Tile;

public class PlayScreen implements Screen {

	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom game;
	public static Hud hud;
	private static Drawable noDialog = null;
	private static float textTimer;
	public int i = 0;

	public PlayScreen(GameSlagyom game, String name) {

		this.game = game;
		new Game(name);
		new LoadingImage();
		gamecam = new OrthographicCamera();
		gamePort = new ScreenViewport(gamecam);

		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch);
	}

	public PlayScreen(GameSlagyom game, String path, String name) {
		new LoadingImage();
		new Game(path, name);
		this.game = game;
		hud = new Hud(game.batch);

		gamecam = new OrthographicCamera();
		gamePort = new ScreenViewport(gamecam);

		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();

	}

	
	@SuppressWarnings({})
	@Override
	public void render(float delta) {

		update(delta);
		hud.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(gamecam.combined);

		game.batch.begin();
		draw();

		game.batch.end();
		hud.stage.draw();

		textTimer += delta;
		if (hud.showDialog)
			if (textTimer > 0.08f) {
				textTimer = 0;
				if (i < hud.textDialog.length()) {
					if (i % 25 == 0)
						hud.textTable.row();
					if (i % 75 == 0)
						hud.textTable.clear();
					drawDialog(String.valueOf(hud.textDialog.charAt(i)));
					i++;
				}
			}

		if (!hud.showDialog) {
			hideDialog();
			i = 0;
		}

	}

	public static void drawDialog(final String text) {
		Drawable dialog = new TextureRegionDrawable(new TextureRegion(new Texture("res/dialogBox.png")));
		if (hud.showDialog) {
			Label dialogLabel = new Label(text, MenuScreen.skin);
			hud.textTable.setBounds(Gdx.graphics.getWidth() / 3 + 15, Gdx.graphics.getHeight() / 8 + 20,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			hud.textTable.setSize(236 * 3, 47 * 4);
			hud.textTable.setBackground(dialog);
			hud.textTable.add(dialogLabel).top();
		}
	}

	public static void hideDialog() {
		hud.textTable.clear();
		hud.textTable.setBackground(noDialog);
	}

	public void update(float dt) {
		moveCharacter(dt);
		if ((Game.character.getX() - Gdx.graphics.getWidth() / 2 > 0
				&& Game.character.getX() + Gdx.graphics.getWidth() / 2 < 1440))
			gamecam.position.x = Game.character.getX();

		if (Game.character.getY() - Gdx.graphics.getHeight() / 2 > 0
				&& Game.character.getY() + Gdx.graphics.getHeight() / 2 < 960)
			gamecam.position.y = Game.character.getY();
		gamecam.update();

	}

	
	private void moveCharacter(float dt) {
		try {
			if (Gdx.input.isKeyPressed(Keys.Z)) {
				Game.character.setVelocity(150f);
				LoadingImage.setFrameDurationCharacter(0.1f);
			} else {
				Game.character.setVelocity(100);
				LoadingImage.setFrameDurationCharacter(0.2f);
			}
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				Game.character.movesLeft(dt);
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				Game.character.movesRight(dt);
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				Game.character.movesUp(dt);
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				Game.character.movesDown(dt);

			} else if (Gdx.input.isKeyJustPressed(Keys.C)) {
				gamecam.zoom -= 0.2;
				gamecam.position.x = Game.character.getX();
				gamecam.position.y = Game.character.getY();

			} else if (Gdx.input.isKeyJustPressed(Keys.V)) {
				gamecam.zoom += 0.2;
				gamecam.position.x = Game.character.getX();
				gamecam.position.y = Game.character.getY();

			} else if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				hud.showDialog = !hud.showDialog;
				hideDialog();
				Game.world.semaphore.release();
			} else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				game.swapScreen(GameSlagyom.State.PAUSE);
				
			} else if (Gdx.input.isKeyJustPressed(Keys.Y)) {
				Game.world.createBattle();
				Game.world.semaphore.acquire();
				game.swapScreen(com.mygdx.game.GameSlagyom.State.BATTLE);
			} else if (Gdx.input.isKeyJustPressed(Keys.B)) {
				Game.world.nextLevel();
			} else
				Game.character.setState(StateDynamicObject.STANDING);
		} catch (InterruptedException e) {

		}
	}

	public synchronized void draw() {
		ListIterator<Tile> it = (ListIterator<Tile>) Game.world.getListTile().listIterator();

		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof StaticObject)
				game.batch.draw(LoadingImage.getTileImage(ob), (float) ((StaticObject) ob).shape.getX(),
						(float) ((StaticObject) ob).shape.getY(), (float) ((StaticObject) ob).shape.getWidth(),
						(float) ((StaticObject) ob).shape.getHeight());
		}
		ListIterator<Item> it2 = Game.world.getListItems().listIterator();
		while (it2.hasNext()) {
			Object ob = (Object) it2.next();
			if (ob instanceof StaticObject)
				game.batch.draw(LoadingImage.getTileImage(ob), (float) ((StaticObject) ob).shape.getX(),
						(float) ((StaticObject) ob).shape.getY(), (float) ((StaticObject) ob).shape.getWidth(),
						(float) ((StaticObject) ob).shape.getHeight());
		}
		Iterator<DynamicObjects> it1 = Game.world.getListDynamicObjects().iterator();
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof DynamicObjects)
				game.batch.draw(LoadingImage.getFrame(ob), ((DynamicObjects) ob).getX(), ((DynamicObjects) ob).getY(),
						((DynamicObjects) ob).getWidth(), ((DynamicObjects) ob).getHeight());
		}
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

		// controlli per la posizione della camera
		if (Gdx.graphics.getWidth() + Game.character.getX() - 1440 > 0
				&& !(Game.character.getX() - Gdx.graphics.getWidth() / 2 < 0)) {
			gamecam.position.x = 1440 - Gdx.graphics.getWidth() + Gdx.graphics.getWidth() / 2;
		} else if (Game.character.getX() - Gdx.graphics.getWidth() / 2 < 0) {
			gamecam.position.x = Gdx.graphics.getWidth() / 2;
		} else
			gamecam.position.x = Game.character.getX();
		if (Gdx.graphics.getHeight() + Game.character.getY() - 960 > 0) {
			gamecam.position.y = 960 - Gdx.graphics.getHeight() + Gdx.graphics.getHeight() / 2;
		} else if (Game.character.getY() - Gdx.graphics.getHeight() / 2 < 0) {
			gamecam.position.y = Gdx.graphics.getHeight() / 2;
		}

	}

	@Override
	public void show() {

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
	}

}