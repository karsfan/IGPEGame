package com.mygdx.game.src.World;

public class ThreadWolrd extends Thread {
	Game game;
	public ThreadWolrd(Game game) {
		this.game = game;
	}
	@Override
	public void run() {
		super.run();
		long start = System.currentTimeMillis();
		while (true) {
			long attuale = System.currentTimeMillis();
			float dt = (attuale - start);
			System.out.println(dt);
			Game.world.update(dt/1000);
			start = attuale;
		}
	}
}
