package com.mygdx.game.src.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import com.mygdx.game.src.Character.DynamicObjects;
import com.mygdx.game.src.Character.Man;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Map;

public class World {

	private ArrayList<DynamicObjects> people;
	public Map[] maps;
	public Battle battle;
	private ThreadWorld thread;
	public Semaphore semaphore;
	int level;
	public boolean remove = false;

	public World() {
		semaphore = new Semaphore(1);
		level = 0;
		people = new ArrayList<DynamicObjects>();
		maps = new Map[2];
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		maps[0] = new Map("res/map/Map1", true, "VILLAGE 1");
		maps[1] = new Map("res/map/newMap", false ,"VILLAGE 2");
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/newMap", false ,"Village two");
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/newMap", false ,"Village two");
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/newMap", false ,"Village two");
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9

		setThread(new ThreadWorld(this, semaphore));

	}

	public World(String path) {
		level = 0;
		semaphore = new Semaphore(0);
		people = new ArrayList<DynamicObjects>();

		maps = new Map[2];
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		maps[0] = new Map(path, true, "VILLAGE 1");
		maps[1] = new Map("res/map/map", false, "VILLAGE 2");
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/map", false, "Village two");
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/map", false, "Village two");
>>>>>>> 2c8db7dc96448855c7e66d24423185617f72eb0f
=======
		maps[0] = new Map("res/map/Map1", true, "Village one");
		maps[1] = new Map("res/map/map", false, "Village two");
>>>>>>> 4675c4d1f931c659ea82ff291bb79348ac51c3f9

		setThread(new ThreadWorld(this, semaphore));
		getThread().start();

	}

	public Map getMap() {
		for (int i = 0; i < 2; i++)
			if (maps[i].current())
				return maps[i];
		return null;
	}

	public boolean addDynamicObject() {
		for (int i = 0; i < GameConfig.numMan; i++) {
			Man man = new Man();
			people.add(man);
		}
		return true;
	}

	public boolean addItems() {
		for (int i = 0; i < GameConfig.numItems; i++) {
			Item item = new Item();
			getListItems().add(item);
		}
		return true;
	}

	public LinkedList<Item> getListItems() {
		return getMap().getListItems();
	}

	public void update(float dt) {

		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<DynamicObjects> it1 = people.iterator();
		while (it1.hasNext()) {
			Object ob = (Object) it1.next();
			if (ob instanceof Man) {
				((Man) ob).update(dt);
			}
		}

		semaphore.release();

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

	public void nextLevel() throws InterruptedException {


		if (level < 1) {
			level++;

			semaphore.acquire();
			people = new ArrayList<DynamicObjects>();
			getMap().setCurrent(false);
			maps[level].setCurrent(true);
			people.add(Game.character);
			while(!addDynamicObject());
			addItems();
			semaphore.release();
		}

	}

	public ThreadWorld getThread() {
		return thread;
	}

	public void setThread(ThreadWorld thread) {
		this.thread = thread;
	}

}
