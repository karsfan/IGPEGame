package com.mygdx.game;

import java.util.Iterator;
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
import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.DynamicObjects.State;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.Tile;
import com.mygdx.game.src.Map.StaticObject.Element;
public class PlayScreen implements Screen {

	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom game;
	public Hud hud;

	
	public PlayScreen(GameSlagyom game, String name) {
		new LoadingImage();
		this.game = game;
		new Game(name);
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
		
		gamecam = new OrthographicCamera();
		gamePort = new ScreenViewport(gamecam);

		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch);

	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.setProjectionMatrix(gamecam.combined);
		
		game.batch.begin();
		draw();
		game.batch.end();
		hud.stage.draw();
	}

	public void drawDialog(final String text) {
		Drawable dialog = new TextureRegionDrawable(new TextureRegion(new Texture("res/dialogBox.png")));
		Drawable noDialog = null;
		if (hud.showDialog) {
			Label dialogLabel = new Label(text, MenuScreen.skin);
			hud.textTable.setBounds(Gdx.graphics.getWidth() / 3 + 15, Gdx.graphics.getHeight() / 8 + 20,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			hud.textTable.setSize(236 * 3, 47 * 4);
			hud.textTable.setBackground(dialog);

			hud.textTable.add(dialogLabel).fillX().top();
		} else {
			hud.textTable.clear();
			hud.textTable.setBackground(noDialog);
		}
	}

	public void update(float dt) {
		// Game.world.update(dt);
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
		} else if (Gdx.input.isKeyJustPressed(Keys.K)) {
			hud.showDialog = !hud.showDialog;
			drawDialog("CIAO");
		} else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.swapScreen(GameSlagyom.State.PAUSE);
		} else if (Gdx.input.isKeyJustPressed(Keys.Y)) {
			Game.world.createBattle();
			game.swapScreen(com.mygdx.game.GameSlagyom.State.BATTLE);
			//GameScreen.swapScreen(GameScreen.State.FIGHTING);
		}
		else
			Game.character.setState(State.STANDING);
	}


	public void draw() {
		Iterator<Object> it = Game.world.getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof Character) {
				game.batch.draw(LoadingImage.getFrameCharacter(((Character) ob).getState()), ((Character) ob).getX(),
						((Character) ob).getY(), ((Character) ob).getWidth(), ((Character) ob).getHeight());
			}
			if (ob instanceof Man) {
				game.batch.draw(LoadingImage.getFrameMan(((Man) ob).getState()), ((Man) ob).getX(), ((Man) ob).getY(),
						((Man) ob).getWidth(), ((Man) ob).getHeight());
			}
			if (ob instanceof Tile) {
				if (((Tile) ob).getElement() == Element.BUILDING) {
					game.batch.draw(LoadingImage.getBuildingImage(),
							(float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.HOME) {
					game.batch.draw(LoadingImage.getHomeImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.THREE) {
					game.batch.draw(LoadingImage.getThreeImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.ROCK) {
					game.batch.draw(LoadingImage.getRockImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.WATER) {
					game.batch.draw(LoadingImage.getWaterImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.GROUND) {
					game.batch.draw(LoadingImage.getGroundImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.GROUND) {
					game.batch.draw(LoadingImage.getGroundImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.FLOOR) {
					game.batch.draw(LoadingImage.getFloorImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}

				if (((Tile) ob).getElement() == Element.ROAD) {
					game.batch.draw(LoadingImage.getRoadImage(), (float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}

				if (((Tile) ob).getElement() == Element.BIGHOME) {
					game.batch.draw(LoadingImage.getBigHomeImage(),
							(float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.FOREST1) {
					game.batch.draw(LoadingImage.getForest1Image(),
							(float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}
				if (((Tile) ob).getElement() == Element.FOREST2) {
					game.batch.draw(LoadingImage.getForest2Image(),
							(float) ((Tile) ob).shape.getX() * 32,
							(float) ((Tile) ob).shape.getY() * 32,
							(float) ((Tile) ob).shape.getWidth(),
							(float) ((Tile) ob).shape.getHeight());
				}

			}
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
	}

}