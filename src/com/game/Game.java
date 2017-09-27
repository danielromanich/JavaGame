package com.daniel.adventure;

import com.daniel.adventure.entity.Entities;
import com.daniel.adventure.entity.GameObjects;
import com.daniel.adventure.entity.entities.Player;
import com.daniel.adventure.landscape.Camera;
import com.daniel.adventure.landscape.Landscape;
import com.daniel.adventure.util.Data;
import com.daniel.adventure.util.Settings;

public class Game {
		
	private GameController controller;
	private Player player;
	private Landscape landscape;
	private GameThread gameThread;
	private Entities entities;
	private GameObjects gameObjects;
	
	private boolean paused = false;
	private boolean running = true;
	
	private boolean dead = false;
	
	private GameCore gameCore;
	private Level level;
	
	public Game(GameCore gameCore, Level level) {
		this.level = level;
		this.gameCore = gameCore;
		this.landscape = new Landscape(this);
		this.player = new Player(landscape.getCamera(), this, landscape.getCollision());
		this.controller = new GameController(player);
		this.entities = new Entities(this);
		this.gameObjects = new GameObjects(this);		
		this.gameThread = new GameThread(this);
	}
	
	public void start() {
		this.gameThread.start();
	}
	
	public void stop() {
		if (this.gameThread.isAlive()) {
			this.running = false;
		}
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public GameController getController() {
		return this.controller;
	}
	
	public Entities getEntities() {
		return this.entities;
	}
	
	public GameObjects getGameObjects() {
		return this.gameObjects;
	}
	
	public Landscape getLandscape() {
		return this.landscape;
	}
	
	public Camera getCamera() {
		return this.landscape.getCamera();
	}
	
	public Settings getSettings() {
		return gameCore.getSettings();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isDead() {
		return this.dead;
	}

	public boolean isPaused() {
		return this.paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
		if (this.dead) {
			new Thread(() -> {
					try {
						Thread.sleep(Data.DEATH_TIMER);
					} catch (InterruptedException e) {}
					gameCore.newGame(level.getResetLevel());
				
			}).start();
		}
	}
}
