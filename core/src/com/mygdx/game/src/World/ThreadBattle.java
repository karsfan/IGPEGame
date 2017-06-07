package com.mygdx.game.src.World;

public class ThreadBattle extends Thread {

	Enemy enemy;

	public ThreadBattle(Enemy enemy) {
		this.enemy = enemy;
	}
	
	@Override
	public void run() {
		super.run();
		long start = System.currentTimeMillis();
		while (true) {
			long attuale = System.currentTimeMillis();
			float dt = (attuale - start);
			enemy.update((float)dt/1000);
			start = attuale;
		}
	}
}
