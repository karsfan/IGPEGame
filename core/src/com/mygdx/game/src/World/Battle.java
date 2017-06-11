package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.CharacterBattle;

public class Battle implements Runnable{

	public static CharacterBattle character;
	public static  Enemy enemy;
	public static int WIDTH;
	public static int HEIGHT;
	public static float gravity;
	
	public Battle() {
		character = null;
		enemy = null;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public Battle(Character character, Enemy enemy) {
		this.character = new CharacterBattle(character);
		//this.enemy = enemy;
		this.enemy = new Enemy(null, 100, 100, null, null, null);
		gravity = 17f;
		WIDTH = 720;
		HEIGHT = 480;
		Game.world.getThread().suspend();
			
	}

	public void moveEnemy(float dt) {
		enemy.update(dt);
	}

	public void jumpEnemy() {

	}

	public static void update(float dt) {
		//moveEnemy(dt);
		character.update(dt);
		enemy.update(dt); 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
