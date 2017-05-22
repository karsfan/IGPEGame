package com.mygdx.game.src.Character;

public class Man {
	public static enum ManType {
		MAN1, MAN2, MAN3
	};

	private String name;
	public int x;
	public int y;
	public int mainX;
	public int mainY;
	public int velocity;
	private String info;

	public Man() {
		x = 200;
		y = 200;
		mainX = 100;
		mainY = 100;
		velocity = 80;
		name = "Ciccio";
		info = "Ciao sono Ciccio";
	}

	public String getInfo() {
		return info;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public boolean moveRight(float dt) {
		if (x + (int) (velocity * dt) < mainX + 30) {
			x += (int) (velocity * dt);
			return true;
		}
		return false;
	}

	public boolean moveLeft(float dt) {
		if (x - (int) (velocity * dt) < mainX - 30) {
			x -= (int) (velocity * dt);
			return true;
		}
		return false;
	}

	public boolean moveUp(float dt) {
		if (y + (int) (velocity * dt) < mainY + 30) {
			y += (int) (velocity * dt);
			return true;
		}
		return false;
	}

	public boolean moveDown(float dt) {
		if (y - (int) (velocity * dt) < mainY - 30) {
			y -= (int) (velocity * dt);
			return true;
		}
		return false;
	}

}
