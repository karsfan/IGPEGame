package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.Character;
import com.mygdx.game.src.Character.CharacterBattle;
import com.mygdx.game.src.Character.CharacterBattle.StateBattleCharacter;

public class Battle {

	public CharacterBattle character;
	public static Enemy enemy;
	public static int WIDTH;
	public static int HEIGHT;
	public static int gravity;
	//ThreadBattle thread;

	public Battle() {
		character = null;
		enemy = null;
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	public Battle(Character character, Enemy enemy) {
		this.character = new CharacterBattle(character);
		this.enemy = enemy;
		WIDTH = 720;
		HEIGHT = 480;
		Game.thread.suspend();
		//thread = new ThreadBattle(this);
		//thread.start();
	}

	public void moveEnemy(float dt) {
		enemy.update(dt);
	}

	public void jumpEnemy() {

	}

	public void update(float dt) {
		moveEnemy(dt);
		if (character.collide() && (character.currentState == StateBattleCharacter.FIGHTINGRIGHT
				|| character.currentState == StateBattleCharacter.FIGHTINGLEFT))
			enemy.decreaseHealth(character.getWeapon());

	}

}
