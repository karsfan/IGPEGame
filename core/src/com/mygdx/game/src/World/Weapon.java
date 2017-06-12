package com.mygdx.game.src.World;

import java.util.Random;

public class Weapon {
	public enum Type {
		SPADA, ARCO, LANCIA, FRECCIA
	};

	public enum Level {
		BASIC, NORMAL, RARE
	};

	float damage;
	Level level;
	Type type;
	private float width;

	public Weapon(Level level, Type type) {

		this.level = level;
		this.type = type;
		switch (this.type) {
		case SPADA:
			setWeapon(type, level);
			break;
		case ARCO:
			setWeapon(type, level);
			break;
		case LANCIA:
			setWeapon(type, level);
			break;
		case FRECCIA:
			damage = 8;
			setWidth(0);
		default:
			break;
		}
	}

	public Weapon(Level level) {
		this.level = level;
		Random rand = new Random();
		int r = rand.nextInt(5);
		switch (r) {
		case 0:
			type = Type.SPADA;
			setWeapon(type, level);
			break;
		case 1:
			type = Type.LANCIA;
			setWeapon(type, level);
			break;
		case 2:
			type = Type.ARCO;
			setWeapon(type, level);
			break;
		default:
			break;
		}
	}

	public void setWeapon(Type type, Level level) {
		switch (type) {
		case SPADA:
			switch (level) {
			case BASIC:
				damage = 10;
				width = 10;
				break;
			case NORMAL:
				damage = 17;
				width = 14;
				break;
			case RARE:
				damage = 30;
				width = 18;
				break;
			default:
				break;
			}
			
		case LANCIA:
			switch (level) {
			case BASIC:
				damage = 8;
				width = 35;
				break;
			case NORMAL:
				damage = 14;
				width = 39;
				break;
			case RARE:
				damage = 22;
				width = 45;
				break;
			default:
				break;
			}
			
		case ARCO:
			switch (level) {
			case BASIC:
				damage = 8;
				width = 15;
				break;
			case NORMAL:
				damage = 14;
				width = 15;
				break;
			case RARE:
				damage = 21;
				width = 15;
				break;
			default:
				break;
			}
		default:
			break;

		}
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
