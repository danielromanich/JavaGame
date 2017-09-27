package com.game;

import com.game.graphic.GameClient;
import com.game.graphic.GameRenderer;
import com.game.input.Keyboard;
import com.game.util.Settings;

public class GameCore {
	
	private GameClient client;
	private GameRenderer gameRenderer;
	private Game game;
	private Settings settings;
	private Keyboard keyboard;
	
	public GameCore() {
		this.keyboard = new Keyboard(this);
		this.settings = new Settings();
		this.gameRenderer = new GameRenderer(this);
		this.client = new GameClient(this); //Initialize this after all of the game has been set up
	}
	
	public static void main(String...args) {
		new GameCore();
	}
	
	public void newGame(Level level) {
		if (this.game != null) {
			this.game.stop();
		}
		this.game = level.getGame();
	}
	
	public Settings getSettings() {
		return this.settings;
	}
	
	public Game getGame() {
		return this.game;
	}

	public GameRenderer getGameRenderer() {
		return this.gameRenderer;
	}
	
	public GameClient getGameClient() {
		return this.client;
	}
	
	public Keyboard getKeyboard() {
		return this.keyboard;
	}

}
