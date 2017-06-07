package com.mygdx.game.src.Map;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.World.ICollidable;

public class Item implements ICollidable {
	public enum Type {
		COIN, POTION, PARCHMENT
	};

	public float x;
	public float y;
	public int width;
	public int height;
	public boolean picked;
	public Type type;
	public String info;
	public static float stateTimer;

	public Item(float x, float y, int width, int height, boolean picked, Type type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.picked = picked;
		this.type = type;
		stateTimer = 0;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean collide(Object e) {
		if (e instanceof Character) {
			if (!((x > ((Character) e).getX() + ((Character) e).getWidth() / 2 - 1
					|| ((Character) e).getX() > x + width)
					|| (y > ((Character) e).getY() + ((Character) e).getHeight() / 2
							|| ((Character) e).getY() > y + height))) {
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

}
