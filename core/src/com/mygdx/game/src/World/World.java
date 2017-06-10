package com.mygdx.game.src.World;

import java.util.ArrayList;
import java.util.Iterator;
import com.mygdx.game.src.Character.DynamicObjects;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Map;
import com.mygdx.game.src.Map.StaticObject;

public class World {

	private static ArrayList<DynamicObjects> people;
	public static Map map;
	public Battle battle;
	//public static ThreadWorld thread;

	public World() {
		
		people = new ArrayList<DynamicObjects>();
		map = new Map("res/map/newMap");
		
		addDynamicObject();
		addItems();
	}
	public World(String path) {
		
		people = new ArrayList<DynamicObjects>();
		map = new Map(path);
		
		addDynamicObject();
		addItems();
	}

	public static Map getMap() {
		return map;
	}
	public void addDynamicObject() {
		for (int i = 0; i < 54; i++) {
			Man man = new Man();
			people.add(man);
		}
	}

	public void addItems() {
		for(int i = 0; i< 54; i++)
		{
			Item item = new Item();
			getListObjects().add(item);
		}
	}

	public void update(float dt) {

		Iterator<StaticObject> it = getListObjects().iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();

			if (ob instanceof Item)
				if (((Item) ob).isPicked()) {
					it.remove();
					continue;
				}
		}

		Iterator<DynamicObjects> it1 = people.iterator();
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof Man) {
				((Man) ob).update(dt);
			}
		}
	}

	public static ArrayList<StaticObject> getListObjects() {
		return map.getStaticObjects();
	}

	public void createBattle() {
		battle = new Battle(Game.character, null);
	}

	public static ArrayList<DynamicObjects> getListDynamicObjects() {
		return people;
	}

}
