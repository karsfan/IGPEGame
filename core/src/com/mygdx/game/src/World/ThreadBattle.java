package com.mygdx.game.src.World;

public class ThreadBattle extends Thread {

	Battle battle;

	public ThreadBattle(Battle battle) {
		this.battle = battle;
	}
	
	@Override
	public void run() {
		super.run();
		long start = System.currentTimeMillis();
		while (true) {
			long attuale = System.currentTimeMillis();
			float dt = (attuale - start);
			Game.world.battle.update(dt/1000);
			start = attuale;
		}
	}
}
