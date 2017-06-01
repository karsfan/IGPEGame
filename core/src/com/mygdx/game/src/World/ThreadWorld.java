package com.mygdx.game.src.World;

public class ThreadWorld extends Thread {
	Game game;
	public ThreadWorld(Game game) {
		this.game = game;
	}
	@Override
	public void run() {
		super.run();
		long start = System.currentTimeMillis();
		while (true) {
			long attuale = System.currentTimeMillis();
			float dt = (attuale - start);
			Game.world.update(dt/1000);
			start = attuale;
			//System.out.println(this.getId());
		}
	}
}
