package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.Character.CharacterBattle.StateBattleCharacter;
import com.mygdx.game.src.World.Game;

public class BattleScreen implements Screen {
	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom game;
	public LoadingImage loadingImage;
	public Hud hud;

	@Override
	public void show() {
		// TODO Auto-generated method stub

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

	private void draw() {

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

		if (Gdx.input.isKeyJustPressed(Keys.S))
			Game.world.battle.character.setState(StateBattleCharacter.DEFENDING);
		else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			Game.world.battle.character.movesLeft(dt);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			Game.world.battle.character.movesRight(dt);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			Game.world.battle.character.jump();
		}
		if (Gdx.input.isKeyJustPressed(Keys.A))
			Game.world.battle.character.fight();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
