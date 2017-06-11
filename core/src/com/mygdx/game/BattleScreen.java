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
import com.mygdx.game.src.Character.DynamicObjects.StateDynamicObject;
import com.mygdx.game.src.World.Battle;
import com.mygdx.game.src.World.Enemy;
import com.mygdx.game.src.World.Game;

public class BattleScreen implements Screen {

	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom gameslagyom;
	public BattleHud hud;

	public BattleScreen(GameSlagyom gameslagyom) {
		this.gameslagyom = gameslagyom;
		gamecam = new OrthographicCamera();
		gamePort = new ExtendViewport(500, 500, gamecam);
		gamePort.apply();
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new BattleHud(gameslagyom.batch);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameslagyom.batch.begin();
		draw();
		gameslagyom.batch.end();
		hud.stage.draw();
		gamePort.apply();
	}

	private void draw() {

		gameslagyom.batch.draw(LoadingImage.getBattleBgImage(), 0, 0);
		@SuppressWarnings("static-access")
		CharacterBattle tmp = Game.world.battle.character;
		gameslagyom.batch.draw(LoadingImage.getBattleFrame(tmp), tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
		@SuppressWarnings("static-access")
		Enemy tmp1 = Game.world.battle.enemy;
		gameslagyom.batch.draw(LoadingImage.getBattleFrame(tmp1), tmp1.getX(), tmp1.getY(), tmp1.getWidth(), tmp1.getHeight());

	}

	public void update(float dt) {

		handleInput(dt);
		hud.update(dt);
		Battle.update(dt);

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private void handleInput(float dt) {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			// va messo in pausa e poi in caso bisogna ritornare nel playscreen
			Game.world.getThread().resume();
			gameslagyom.swapScreen(State.PLAYING);
		}
		moveCharacter(dt);
	}

	@SuppressWarnings("static-access")
	private void moveCharacter(float dt) {

		if (Gdx.input.isKeyJustPressed(Keys.S))
			Game.world.battle.character.setState(StateDynamicObject.DEFENDING, dt);
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			Game.world.battle.character.jump(dt);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (Gdx.input.isKeyJustPressed(Keys.A))
				Game.world.battle.character.fightLeft(dt);
			else
				Game.world.battle.character.movesLeft(dt);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (Gdx.input.isKeyJustPressed(Keys.A))
				Game.world.battle.character.fightRight(dt);
			else
				Game.world.battle.character.movesRight(dt);
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
