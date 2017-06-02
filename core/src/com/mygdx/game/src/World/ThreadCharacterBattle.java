package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.CharacterBattle;

public class ThreadCharacterBattle extends Thread {
	CharacterBattle character;

	public ThreadCharacterBattle(CharacterBattle character2) {
		this.character = character2;
	}

	@Override
	public void run() {
		super.run();

		while (true) {
			character.update(0.025f);
			try {
				sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
