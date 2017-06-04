package com.mygdx.game.src.World;

public class Weapon {
	public enum Type {
		SPADA, ARCO, LANCIA, FRECCIA
	};

	public enum Level {
		BASIC, NORMAL, RARE
	};

	public String name;
	float damage;
	Level level;
	Type type;
	private float width;

	public Weapon(String name, Level level, Type type) {

		this.name = name;
		this.level = level;
		this.type = type;
		switch (this.type) {
		case SPADA:
			damage = 10;
			setWidth(10);
			break;
		case ARCO:
			damage = 8;
			setWidth(0);
			break;
		case LANCIA:
			damage = 8;
			setWidth(35);
			break;
		case FRECCIA:
			damage = 8;
			setWidth(0);
		default:
			break;
		}
	}

	public Weapon() {
		name = new String();

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*
	 * public void upgrade(Bag bag) { if (level < 3) { level++; powerPoints =
	 * level * 20; damage += 20; bag.deleteParchments(level); } }
	 */

	public float getDamage() {
		return damage;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}
