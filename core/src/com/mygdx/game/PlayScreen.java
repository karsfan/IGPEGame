package com.mygdx.game;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.World.Enemy;
import com.mygdx.game.src.World.Game;
import com.mygdx.game.src.World.Tile;
import com.mygdx.game.src.World.World.Element;

public class PlayScreen implements Screen {

	public OrthographicCamera gamecam;
	private Viewport gamePort;
	public GameSlagyom game;
	public LoadingImage loadingImage;
	public Hud hud;

	/*public PlayScreen(GameSlagyom game) {
		this.game = game;
		new Game();
		gamecam = new OrthographicCamera();
		// gamePort = new StretchViewport(440, 260, gamecam);
		gamePort = new ScreenViewport(gamecam);
		loadingImage = new LoadingImage();
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch, gamecam, gamePort);

	}*/
	
	public PlayScreen(GameSlagyom game, String name) {
		this.game = game;
		new Game(name);
		gamecam = new OrthographicCamera();
		// gamePort = new StretchViewport(440, 260, gamecam);
		gamePort = new ScreenViewport(gamecam);
		loadingImage = new LoadingImage();
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch, gamecam, gamePort);

	}
	
	public PlayScreen(GameSlagyom game, String path, String name) {
		this.game = game;
		new Game(path, name);
		gamecam = new OrthographicCamera();
		// gamePort = new StretchViewport(440, 260, gamecam);
		gamePort = new ScreenViewport(gamecam);
		loadingImage = new LoadingImage();
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch, gamecam, gamePort);

	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.setProjectionMatrix(gamecam.combined);

		game.batch.begin();
		draw(game.batch);
		game.batch.end();
		hud.stage.draw();


	}

	public void update(float dt) {
		handleInput(dt);

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
		}
	
	}

	private void handleInput(float dt) {
		Game.world.update(dt);
	}

	public void draw(SpriteBatch batch) {
		Iterator<Object> it = Game.world.getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (ob instanceof Character) {
				game.batch.draw(LoadingImage.getFrameCharacter(((Character) ob).getState()), ((Character) ob).getX(),
						((Character) ob).getY(), ((Character) ob).getWidth(), ((Character) ob).getHeight());
			}
			if (ob instanceof Enemy) {
				game.batch.draw(LoadingImage.getFrameEnemy(((Enemy) ob).getState(), (Enemy) ob), ((Enemy) ob).x,
						((Enemy) ob).y, ((Enemy) ob).getWidth(), ((Enemy) ob).getHeight());
			}
			if (ob instanceof Tile) {
				if (((Tile) ob).getElement() == Element.BUILDING) {
					game.batch.draw(LoadingImage.getBuildingImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.HOME) {
					game.batch.draw(LoadingImage.getHomeImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.THREE) {
					game.batch.draw(LoadingImage.getThreeImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.ROCK) {
					game.batch.draw(LoadingImage.getRockImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.WATER) {
					game.batch.draw(LoadingImage.getWaterImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.GROUND) {
					game.batch.draw(LoadingImage.getGroundImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.GROUND) {
					game.batch.draw(LoadingImage.getGroundImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.FLOOR) {
					game.batch.draw(LoadingImage.getFloorImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}

				if (((Tile) ob).getElement() == Element.ROAD) {
					game.batch.draw(LoadingImage.getRoadImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}

				if (((Tile) ob).getElement() == Element.BIGHOME) {
					game.batch.draw(LoadingImage.getBigHomeImage(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.FOREST1) {
					game.batch.draw(LoadingImage.getForest1Image(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
				}
				if (((Tile) ob).getElement() == Element.FOREST2) {
					game.batch.draw(LoadingImage.getForest2Image(), (float) ((Tile) ob).getPoint().getX() * 32,
							(float) ((Tile) ob).getPoint().getY() * 32, (float) ((Tile) ob).getSize().getWidth(),
							(float) ((Tile) ob).getSize().getHeight());
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
