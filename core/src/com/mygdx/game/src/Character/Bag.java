package com.mygdx.game.src.Character;

import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Item.Level;
import com.mygdx.game.src.Map.StaticObject.Element;
import com.mygdx.game.src.World.Weapon;

public class Bag {
	int capacity;
	public ArrayList<Item> bagItems; 
	Weapon secondary_weapon;

	public Bag() {
		capacity = 50;
		secondary_weapon = null;
		bagItems = new ArrayList<Item>();
	}

	public boolean addTool(Item item) {
		if (bagItems.size() < capacity) {
			System.out.println(bagItems.size());
			bagItems.add(item);
			return true;
		}
		return false;
	}

	public void addWeapon(Weapon weapon) {
		secondary_weapon = weapon;
	}

	public void deleteParchments(Item item) {
		Iterator<Item> itParchment = bagItems.iterator();
		while (itParchment.hasNext()) {
			Item it = (Item) itParchment.next();
			if (it == item){
				itParchment.remove();
				continue;
			}
		} // it controls that the numbers is sufficient to upgrade
	}

	public boolean deletePotion(Item potion) {
		Iterator <Item> itPotion = bagItems.iterator();
		while (itPotion.hasNext()) {
			Item tool = (Item) itPotion.next();
			if (tool == potion) {
				itPotion.remove();
				return true;
			}
		}
		return false;
	}
	

	public int getNumberOf(Element element, Level level) {
		int numberOf = 0;
		Iterator <Item> itemIterator = bagItems.iterator();
		while (itemIterator.hasNext()) {
			Item searching = (Item) itemIterator.next();
			if (searching.getElement() == element && searching.level == level) {
				numberOf++;
			}
		}
		return numberOf;
	}
	
	public void removeItem (Element element, Level level) {
		Iterator <Item> itemIterator = bagItems.iterator();
		while (itemIterator.hasNext()) {
			Item searching = (Item) itemIterator.next();
			if (searching.getElement() == element && searching.level == level) {
				itemIterator.remove();
				break;
			}
		}
	}

}
