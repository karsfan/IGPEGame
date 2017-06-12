package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.CharacterBattle;

public class Battle implements Runnable {

	public static CharacterBattle character;
	public static Enemy enemy;
	public static int WIDTH;
	public static int HEIGHT;

	public Battle() {
		character = null;
		enemy = null;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public Battle(Character character, Enemy enemy) {
		this.character = new CharacterBattle(character);
		// this.enemy = enemy;
		this.enemy = new Enemy(null, 100, 100, null, null, null);
		WIDTH = 720;
		HEIGHT = 480;

	}

	public void moveEnemy(float dt) {
		enemy.update(dt);
	}

	public void jumpEnemy() {

	}

	public static boolean update(float dt) {
		// moveEnemy(dt);
		if (enemy.health <= 0){
			System.out.println("Hai vinto");
			return true;
		}
		if (character.getHealth() <= 0){
			System.out.println("Hai perso. Riprova quando sarai pronto!");
			return true;
		}
		character.update(dt);
		enemy.update(dt);
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
