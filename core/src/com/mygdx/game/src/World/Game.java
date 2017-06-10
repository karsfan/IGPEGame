package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;

public class Game {

	public static World world;
	public static Character character;

	@SuppressWarnings("static-access")
	public Game(String name) {
		
		character = new Character(name);
		world = new World();
		
		world.getListDynamicObjects().add(character);
		
	}
	
	@SuppressWarnings("static-access")
	public Game(String path, String name) {
			
		character = new Character(name);
	
		world = new World(path);
		
		world.getListDynamicObjects().add(character);
	
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
