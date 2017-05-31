package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameSlagyom.State;
import com.mygdx.game.src.Character.CharacterBattle;
import com.mygdx.game.src.Character.CharacterBattle.StateBattleCharacter;
import com.mygdx.game.src.World.Game;

public class BattleScreen implements Screen {

	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom game;
	public BattleHud hud;

	public BattleScreen(GameSlagyom game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		// gamePort = new ScreenViewport();
		gamePort = new ExtendViewport(500, 500, gamecam);
		gamePort.apply();
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new BattleHud(game.batch);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		draw();
		game.batch.end();
		hud.stage.draw();
		gamePort.apply();
	}

	private void draw() {
		
		game.batch.draw(LoadingImage.getBattleBgImage(), 0, 0);
		CharacterBattle tmp = Game.world.battle.character;
		game.batch.draw(LoadingImage.getBattleFrameCharacter(tmp.getCurrentState()), tmp.getX(), tmp.getY(),
				tmp.getWidth(), tmp.getHeight());
	}

	public void update(float dt) {
		Game.world.battle.character.update(dt);
		handleInput(dt);
		// hud.update(dt);
	}

	@SuppressWarnings({ "deprecation" })
	private void handleInput(float dt) {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			// va messo in pausa e poi in caso bisogna ritornare nel playscreen
			Game.thread.resume();
			game.swapScreen(State.PLAYING);
		}

		moveCharacter(dt);

	}

	private void moveCharacter(float dt) {

		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			Game.world.battle.character.fightRight();
		}
		if (Gdx.input.isKeyJustPressed(Keys.S))
			Game.world.battle.character.setState(StateBattleCharacter.DEFENDING);
		else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			Game.world.battle.character.movesLeft(dt);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (Gdx.input.isKeyJustPressed(Keys.A)) {
				Game.world.battle.character.fightRight();
			} else
				Game.world.battle.character.movesRight(dt);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			Game.world.battle.character.jump(dt);
		} else {
			Game.world.battle.character.stand();
		}
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		gamecam.position.set((float) gamecam.viewportWidth / 2, (float) gamecam.viewportHeight / 2, 0);
		gamecam.update();
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
		// TODO Auto-generated method stub

	}

}
