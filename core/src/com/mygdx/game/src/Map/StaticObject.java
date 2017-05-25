package com.mygdx.game.src.Map;


import java.awt.Rectangle;

public class StaticObject {
	
	public enum Element {
		HOME, THREE, FOREST1, FOREST2, GROUND, BUILDING, WATER, ROCK, FLOOR, ROAD, BIGHOME, SHOP
	};
	protected Element element;
	public Rectangle shape;
	
	public StaticObject(){
		element = null;
		shape = new Rectangle();
		
	}
}
