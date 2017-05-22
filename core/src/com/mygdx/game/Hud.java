package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
	public SpriteBatch spriteBatch; 
	
	public Stage stage; 
	private Viewport viewport;
	
	private Integer score; 
	private Integer health; 
	
	Label scoreLabel;
	Label healthLabel; 
	
	Label villageLabel; 
	
	public Hud (SpriteBatch sb, OrthographicCamera gamecam, Viewport gamePort) {
		spriteBatch = sb; 
		score = 0;
		health = 100; 
		
		viewport = new FitViewport(1200, 1200, new OrthographicCamera());
	//	viewport = gamePort;
		stage = new Stage(viewport, sb);
		
		Table table = new Table(); 
		table.top(); // la allinea sopra al centro
		table.setFillParent(true); 
		
		
		
		
		//FileHandle fileFont = new FileHandle("src/res/vcr.ttf");
		BitmapFont font = new BitmapFont();//fileFont);
		
		font.getData().setScale(2);
		
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(font, Color.WHITE));
		healthLabel = new Label(String.format("%03d", health), new Label.LabelStyle(font, Color.WHITE));
		villageLabel = new Label(String.format("VILLAGE 1"), new Label.LabelStyle(font, Color.WHITE));
		
		table.add(villageLabel).expandX().pad(20);
		table.add(scoreLabel).expandX().pad(20);
		table.add(healthLabel).expandX().pad(20);
		table.row(); // nuova colonna
		
		stage.addActor(table);


		
	}
	
}