package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.Character.Character;

public class BattleHud {
	
	public SpriteBatch spriteBatch;
	public Stage stage;
	private Viewport viewport;
	private Label healthLabel;
	private Label nameLabel;
	
	Integer health;
	public BattleHud(SpriteBatch batch) {
		spriteBatch = batch;
		
		viewport = new FitViewport(1200,1200, new OrthographicCamera());
		stage = new Stage(viewport, spriteBatch);
		
		health = 100;
		Table table = new Table();
		table.top(); // la allinea sopra al centro
		table.setFillParent(true);


		nameLabel = new Label(Character.name, MenuScreen.skin);
		healthLabel = new Label(String.format("%03d", health), MenuScreen.skin);
	
		table.add(nameLabel).expandX().pad(20);
		table.add(healthLabel).expandX().pad(20);
		table.row(); // nuova colonna

		stage.addActor(table);

	}
	public void update(float dt){
		
		health ++;
		healthLabel.setText(String.format("%03d",health));
	}
}
