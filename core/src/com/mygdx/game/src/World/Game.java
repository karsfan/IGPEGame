package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Map.Item;
import com.mygdx.game.src.Map.Item.Level;
import com.mygdx.game.src.Map.StaticObject.Element;

public class Game {

	public static World world;
	public static Character character;

	//public static ThreadWorld thread;
	public static Item coin;

	public static Item potion;

	@SuppressWarnings("static-access")
	public Game(String name) {
		potion = new Item (100, 600, Element.POTION, Level.FIRST);
		coin = new Item(100, 700, Element.COIN, Level.FIRST);
		character = new Character(name);
		world = new World();
		
		world.getListObjects().add(coin);
		world.getListObjects().add(potion);
		
		world.getListDynamicObjects().add(character);
		//thread = new ThreadWorld(this);
		//thread.start();
	}
	
	@SuppressWarnings("static-access")
	public Game(String path, String name) {
		potion = new Item (100, 600, Element.POTION, Level.FIRST);
		coin = new Item(100, 700, Element.COIN, Level.FIRST);
		
		character = new Character(name);
	
		world = new World(path);
		world.getListObjects().add(coin);
		world.getListObjects().add(potion);
		
		world.getListDynamicObjects().add(character);
		//thread = new ThreadWorld(this);
		//thread.start();
	}

	public void play() {
	
	}


	public void initialize() {
		setWorld(new World());

	}

	public void exit() {

	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World world) {
		Game.world = world;
	}

}
