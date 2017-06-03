package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.CharacterBattle;
import com.mygdx.game.src.Character.DynamicObjects.StateDynamicObject;

public class Battle implements Runnable{

	public CharacterBattle character;
	public static Enemy enemy;
	public static int WIDTH;
	public static int HEIGHT;
	public static int gravity;
	ThreadCharacterBattle threadCharacter;

	public Battle() {
		character = null;
		enemy = null;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public Battle(Character character, Enemy enemy) {
		this.character = new CharacterBattle(character);
		//this.enemy = enemy;
		this.enemy = new Enemy(null, 100, 100, null, null, null);
		gravity = 17;
		WIDTH = 720;
		HEIGHT = 480;
		Game.thread.suspend();
		threadCharacter = new ThreadCharacterBattle(this.character, this.enemy);
		threadCharacter.start();
	
	}

	public void moveEnemy(float dt) {
		enemy.update(dt);
	}

	public void jumpEnemy() {

	}

	public void update(float dt) {
		moveEnemy(dt);
		if (character.collide() && (character.character.currentState == StateDynamicObject.FIGHTINGRIGHT
				|| character.character.currentState == StateDynamicObject.FIGHTINGLEFT))
			enemy.decreaseHealth(character.getWeapon());		 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
