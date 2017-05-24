package com.mygdx.game.src.Map;


import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class StaticObject {
	
	public enum Element {
		HOME, THREE, FOREST1, FOREST2, GROUND, BUILDING, WATER, ROCK, CHARACTER, ENEMY, PARCHMENTLEVEL1, PARCHMENTLEVEL2, PARCHMENTLEVEL3, POTION_H_L1, POTION_H_L2, POTION_H_L3, FLOOR, ROAD, BIGHOME
	};
	protected Element element;
	public RectangleMapObject shape;
	
	public StaticObject(){
		element = null;
		shape = new RectangleMapObject();
		
	}
}
