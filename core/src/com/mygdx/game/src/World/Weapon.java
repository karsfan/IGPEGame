package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Bag;
import com.mygdx.game.src.Tool.Potion;

public class Weapon {
	public enum Type {
		SPADA, ARCO, LANCIA
	};
	
	public enum Level {
		
	};
	
	public String name;
	float damage;
	Level level;
	Type type;
	int powerPoints;
	float width;

	public Weapon(String name, float damage, Level level, Type type) {

		this.name = name;
		this.damage = damage;
		this.level = level;
		this.type = type;
		switch(type){
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

	/*public void upgrade(Bag bag) {
		if (level < 3) {
			level++;
			powerPoints = level * 20;
			damage += 20;
			bag.deleteParchments(level);
		}
	}*/

	public void upgradePP(Bag bag, Potion potion) {
		if (bag.deletePotion(potion))
			powerPoints += 10 * potion.getLevel();
		else
			;// ATTENZIONE non ci sono potion per aumentare l'i PP dell'arma
	}

	public float getDamage() {
		return damage;
	}
}
