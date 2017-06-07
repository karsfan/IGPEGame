package com.mygdx.game.src.World;

import com.mygdx.game.src.Character.CharacterBattle;

public class ThreadCharacterBattle extends Thread {
	CharacterBattle character;


	public ThreadCharacterBattle(CharacterBattle character2) {
		this.character = character2;		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		super.run();

		long start = System.currentTimeMillis();
		while (true) {
			long attuale = System.currentTimeMillis();
			float dt = (attuale - start);
			character.update((float)(dt/1000));
			start = attuale;
			suspend();
		}
	}
}
