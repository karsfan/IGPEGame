package com.mygdx.game.src.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import com.mygdx.game.src.Character.DynamicObjects;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Map;

public class World {

	private static ArrayList<DynamicObjects> people;
	public static Map map;
	public Battle battle;
	public static ThreadWorld thread;

	public World() {
		
		people = new ArrayList<DynamicObjects>();
		map = new Map("res/map/newMap");
		
		addDynamicObject();
		addItems();
		thread = new ThreadWorld(this);
		thread.start();
	}
	@SuppressWarnings("deprecation")
	public World(String path) {
		
		people = new ArrayList<DynamicObjects>();
		map = new Map(path);
		
		addDynamicObject();
		addItems();
		thread = new ThreadWorld(this);
		thread.start();
		thread.suspend();
	}

	public static Map getMap() {
		return map;
	}
	public void addDynamicObject() {
		for (int i = 0; i < 1500; i++) {
			Man man = new Man();
			people.add(man);
		}
	}

	public void addItems() {
		for(int i = 0; i< 554; i++)
		{
			Item item = new Item();
			getListItems().add(item);
		}
	}
	
	@SuppressWarnings("static-access")
	public LinkedList<Item> getListItems(){
		return map.getListItems();
	}

	public  synchronized void update(float dt) {
		
		Iterator<DynamicObjects> it1 = people.iterator();
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof Man) {
				((Man) ob).update(dt);
			}
		}
	}

	public static LinkedList<Tile> getListTile() {
		return map.getListTile();
	}

	public void createBattle() {
		battle = new Battle(Game.character, null);
	}

	public static ArrayList<DynamicObjects> getListDynamicObjects() {
		return people;
	}

}
