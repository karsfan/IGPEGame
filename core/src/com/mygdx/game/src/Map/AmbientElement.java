package com.mygdx.game.src.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AmbientElement {
	public static enum ElementA {
		FOREST, BUILDING, LAKE
	};

	public int x;
	public int y;
	public ElementA element;

	public AmbientElement(ElementA element) {
		x = 300;
		y = 700;
		this.element = element;
	}

	public void draw(SpriteBatch batch) {

		Texture img = new Texture("C:/Users/Nicholas/Desktop/b.jpg");
		batch.draw(img, x, y);
	}

}
