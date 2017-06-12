package com.mygdx.game.src.World;

import java.util.ArrayList;

import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.World.Enemy.Level;

public class Pack {
	Weapon weapon;
	ArrayList<Item> items;

	public Pack(Level level) {
		items = new ArrayList<Item>();
		switch (level) {
		case EASY:
			weapon = new Weapon(Weapon.Level.BASIC);
			for (int i = 0; i < 10; i++) {
				Item item = new Item();
				items.add(item);
			}

		case MEDIUM:
			weapon = new Weapon(Weapon.Level.NORMAL);
			for (int i = 0; i < 15; i++) {
				Item item = new Item();
				items.add(item);
			}

		case HARD:
			weapon = new Weapon(Weapon.Level.RARE);
			for (int i = 0; i < 30; i++) {
				Item item = new Item();
				items.add(item);
			}
		default:
			break;
		}
	}
}
