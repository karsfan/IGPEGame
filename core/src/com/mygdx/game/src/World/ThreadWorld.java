package com.mygdx.game.src.World;

public class ThreadWorld extends Thread {
	World world;


	public ThreadWorld(World world) {
		this.world = world;
	}

	@Override
	public void run() {
		super.run();
		
			long start = System.currentTimeMillis();
			while (true) {
				long attuale = System.currentTimeMillis();
				float dt = (float) (attuale - start);

				world.update((float) dt / 1000);
				start = attuale;
			}
		}
	
}
