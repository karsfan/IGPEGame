package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.World.Game;

public class BattleScreen implements Screen {

	public OrthographicCamera gamecam;
	public Viewport gamePort;
	public GameSlagyom game;
	public Hud hud;

	public BattleScreen (GameSlagyom game) {
		this.game = game; 
		gamecam = new OrthographicCamera();
		gamePort = new ScreenViewport(gamecam);
		gamecam.position.x = Game.character.getX();
		gamecam.position.y = Game.character.getY();
		hud = new Hud(game.batch, gamecam, gamePort);
	}
	
	@Override
	public void show() {

	}
	
	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		draw();
		game.batch.end();
		hud.stage.draw();
	}

	private void draw() {
		game.batch.draw(LoadingImage.getBattleBgImage(), 100, 100);
	}

	public void update(float dt) {
		handleInput(dt);
	}
	@SuppressWarnings("static-access")
	private void handleInput(float dt){

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			//va messo in pausa e poi in caso bisogna ritornare nel playscreen
			game.setScreen(game.playScreen);
			//game.swapScreen(State.PLAYING);
		}

		moveCharacter(dt);
		
	}
	private void moveCharacter(float dt) {

		/*if (Gdx.input.isKeyJustPressed(Keys.S))
			Game.world.battle.character.setState(StateBattleCharacter.DEFENDING);
		else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			Game.world.battle.character.movesLeft(dt);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			Game.world.battle.character.movesRight(dt);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			Game.world.battle.character.jump();
		}
		if (Gdx.input.isKeyJustPressed(Keys.A))
			Game.world.battle.character.fight();*/
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
