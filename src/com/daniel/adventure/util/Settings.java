package com.daniel.adventure.util;

public class Settings {
	
	private boolean muted, debugPlayer, debugEntities, debugObjects, debugTiles;
	private int volume = 50;
	
	public Settings() {
		
	}
	
	public int getVolume() {
		return this.volume;
	}
	
	public boolean isMuted() {
		return this.muted;
	}
	
	public boolean isDebuggingPlayer() {
		return this.debugPlayer;
	}
	
	public boolean isDebuggingEntities() {
		return this.debugEntities;
	}
	
	public boolean isDebuggingObjects() {
		return this.debugObjects;
	}
	
	public boolean isDebuggingTiles() {
		return this.debugTiles;
	}
	
	public void setDebugPlayer(boolean debug) {
		this.debugPlayer = debug;
	}
	
	public void setDebugEntities(boolean debug) {
		this.debugEntities = debug;
	}
	
	public void setDebugObjects(boolean debug) {
		this.debugObjects = debug;
	}
	
	public void setDebugTiles(boolean debug) {
		this.debugTiles = debug;
	}
	
	public void setVolue(int volume) {
		if (volume >= 0 && volume <= 100)  {
			this.volume = volume;
		}
	}
	
	public void setMuted(boolean muted) {
		this.muted = muted;
	}

}
