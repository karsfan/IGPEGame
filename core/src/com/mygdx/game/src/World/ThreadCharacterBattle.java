package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.CharacterBattle;

public class ThreadCharacterBattle extends Thread {
	CharacterBattle character;
	Enemy enemy;

	public ThreadCharacterBattle(CharacterBattle character2, Enemy enemy) {
		this.character = character2;
		this.enemy = enemy;
	}

	@Override
	public void run() {
		super.run();

		while (true) {
			character.update(0.35f);
			enemy.update(0.35f);
			try {
				sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
