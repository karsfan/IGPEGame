package com.mygdx.game.src.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import com.mygdx.game.src.Character.DynamicObjects;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Map;

public class World {

	private ArrayList<DynamicObjects> people;
	public Map[] maps;
	public Battle battle;
	private  ThreadWorld thread;
	int level;

	public World() {
		level = 0;
		people = new ArrayList<DynamicObjects>();
		maps = new Map[2];
		maps[0] = new Map("res/map/newMap", true);
		maps[1] = new Map("res/map/map", false);

		//addDynamicObject();
		// addItems();
		 setThread(new ThreadWorld(this));
		// thread.start();
	}

	@SuppressWarnings("deprecation")
	public World(String path) {
		level = 0;
		people = new ArrayList<DynamicObjects>();
		
		maps = new Map[2];
		maps[0] = new Map(path, true);
		maps[1] = new Map("res/map/map", false);
		
		setThread(new ThreadWorld(this));
		getThread().start();
		getThread().suspend();
	}

	public Map getMap() {
		for (int i = 0; i < 2; i++)
			if (maps[i].current())
				return maps[i];
		return null;
	}

	public boolean addDynamicObject() {
		// people.clear();
		for (int i = 0; i < 50; i++) {
			Man man = new Man();
			people.add(man);
		}
		return true;
	}

	public boolean addItems() {
		for (int i = 0; i < 554; i++) {
			Item item = new Item();
			getListItems().add(item);
		}
		return true;
	}

	public LinkedList<Item> getListItems() {
		return getMap().getListItems();
	}

	public synchronized void update(float dt) {

		Iterator<DynamicObjects> it1 = people.iterator();
		// System.out.println("qui");
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof Man) {
				((Man) ob).update(dt);
			}
		}
	}

	public LinkedList<Tile> getListTile() {
		return getMap().getListTile();
	}

	public void createBattle() {
		battle = new Battle(Game.character, null);
	}

	public ArrayList<DynamicObjects> getListDynamicObjects() {
		return people;
	}

	public void nextLevel() {
		getThread().suspend();
		if (level < 2)
			level++;
		people.removeAll(people);
		getMap().setCurrent(false);
		maps[level].setCurrent(true);
		getThread().resume();
		System.out.println("quiiiii");
	}

	public ThreadWorld getThread() {
		return thread;
	}

	public void setThread(ThreadWorld thread) {
		this.thread = thread;
	}

}
