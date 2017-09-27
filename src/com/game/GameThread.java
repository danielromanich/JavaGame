package com.game;

import com.game.util.Data;

public class GameThread extends Thread implements Runnable {
	
	private Game game;
	
	public GameThread(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		while (game.isRunning()) {
			long start = System.currentTimeMillis();
			if (!game.isPaused()) {
				game.getPlayer().tick();
				game.getEntities().tickEntities();
			}
			long delta = System.currentTimeMillis() - start;
			try {
				Thread.sleep(1000/ Data.PHYSICS_FPS - delta < 0 ? 0 : 1000/Data.PHYSICS_FPS - delta);
			} catch (InterruptedException e) {}
		}
	}

}
