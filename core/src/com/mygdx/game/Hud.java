package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.src.World.Game;

public class Hud {
	
	public SpriteBatch spriteBatch;
	public Stage stage;
	private Viewport viewport;
	
	private Label healthLabel;
	private Label nameLabel;
	private Label villageLabel;
	
	public String textDialog = ""; 
	public boolean showDialog = true;
	public Table textTable = new Table();
	Integer health;
	public Hud(SpriteBatch sb) {
		spriteBatch = sb;
		health = 90;
		viewport = new FitViewport(1200, 1200, new OrthographicCamera());
		
		stage = new Stage(viewport, sb);

		Table table = new Table();
		table.top(); // la allinea sopra al centro
		table.setFillParent(true);

		nameLabel = new Label(Game.character.name, MenuScreen.skin);
		healthLabel = new Label(String.format("%03d", health), MenuScreen.skin);
		villageLabel = new Label(String.format("VILLAGE 1"), MenuScreen.skin);

		table.add(nameLabel).expandX().pad(20);
		table.add(villageLabel).expandX().pad(20);
		table.add(healthLabel).expandX().pad(20);
		table.row(); // nuova colonna

		stage.addActor(table);

		//Drawable dialog = new TextureRegionDrawable(new TextureRegion(new Texture("res/dialogBox.png")));
		/*if (showDialog) {
			TextButton dialogLabel = new TextButton("\nDIALOGO BLA BLA BLA BLA BLA BLA\n DIALOGO BLA BLA BLA BLA BLA BLA \n DIALOGO BLA BLA BLA BLA BLA BLA \n", MenuScreen.skin);
			
			textTable.setBounds(Gdx.graphics.getWidth()/3+15, Gdx.graphics.getHeight()/8+20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			textTable.setSize(236 * 3, 47 * 4);
			textTable.setBackground(dialog);

			textTable.add(dialogLabel);
		}*/
		stage.addActor(textTable);
	}

}