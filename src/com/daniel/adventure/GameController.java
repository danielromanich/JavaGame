package com.daniel.adventure;

import com.daniel.adventure.entity.entities.Player;

public class GameController {
	
	private Player player;
	
	public GameController(Player player) {
		this.player = player;
	}
	
	public void setHorizontalVelocity(int number) {
		player.setVelocityX(number);
	}
	
	public void setVerticalVelocity(int number) {
		player.setVelocityY(number);
	}

}
