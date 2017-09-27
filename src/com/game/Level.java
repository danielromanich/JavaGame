package com.game;

import java.awt.Image;

import com.game.res.ResourceLoader;

public class Level {
	
	protected Game game;
	protected GameCore gameCore;
	
	public Level(GameCore gameCore) {
		this.gameCore = gameCore;
		this.game = new Game(gameCore, this);
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public Level getResetLevel() {
		return this;
	}
	
	public Image getBackground() {
		return ResourceLoader.getImage("backgrounds/normal");
	}

}
