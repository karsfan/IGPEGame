package com.mygdx.game.src.Map;

import java.awt.Rectangle;

import com.mygdx.game.src.Character.Character;

public class Item extends StaticObject {

	public enum Level {
		FIRST, SECOND, THIRD
	}

	public boolean picked;

	public String info;
	public static float stateTimer;
	public Level level;

	public Item(float x, float y, Element element, Level level) {

		switch (element) {
		case COIN:
			shape = new Rectangle((int) x, (int) y, 11, 11);
			break;
		case POTION:
			shape = new Rectangle((int) x, (int) y, 14, 14);
			break;
		case PARCHMENT:
			shape = new Rectangle((int) x, (int) y, 10, 10);
			break;
		default:
			break;
		}

		this.element = element;
		this.level = level;
		picked = false;
		stateTimer = 0;
	}

	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public float getX() {
		return shape.x;
	}

	public void setX(int x) {
		this.shape.x = (int) x;
	}

	public int getY() {
		return shape.y;
	}

	public void setY(int y) {
		this.shape.y = y;
	}

	public int getWidth() {
		return shape.width;
	}

	public void setWidth(int width) {
		this.shape.width = width;
	}

	public int getHeight() {
		return shape.height;
	}

	public void setHeight(int height) {
		this.shape.height = height;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		System.out.println("racc");
		this.picked = picked;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	@Override
	public boolean collide(Object e) {
		if (e instanceof Character) {
			if (!((shape.x > ((Character) e).getX() + ((Character) e).getWidth() / 2 
					|| ((Character) e).getX() > shape.x + shape.width)
					|| (shape.y > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > shape.y + shape.height))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean collide() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getPick() {
		// TODO Auto-generated method stub
		return false;
	}

}
