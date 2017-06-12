package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.Woman;

public class Game {

	public static World world;
	public static Character character;

	

	public Game(String name) {

		character = new Character(name);
		world = new World();
		while(!world.addDynamicObject());
		while(!world.addItems());
		Woman w = new Woman();
		//world.getListDynamicObjects().add(w);
		world.getListDynamicObjects().add(character);
		world.getThread().start();
		
	}

	public Game(String path, String name) {

		character = new Character(name);
		world = new World(path);
		while(!world.addDynamicObject());
		while(!world.addItems());
		world.getListDynamicObjects().add(character);

	}


	public void initialize() {
		setWorld(new World());

	}

	public void exit() {

	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		Game.world = world;
	}

}
