package com.game.input;

import java.awt.event.KeyEvent;

import com.game.GameCore;

public class Keyboard {
	
	private GameCore game;
	
	public Keyboard(GameCore game) {
		this.game = game;
	}
	
	public void onKeyEntered(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				game.getGame().getPlayer().right = true;//game.getController().setHorizontalVelocity(Data.PLAYER_SPEED);
				game.getGame().getPlayer().setLastDirection(1);
			break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				game.getGame().getPlayer().left = true;
				game.getGame().getPlayer().setLastDirection(-1);
				//game.getController().setHorizontalVelocity(-Data.PLAYER_SPEED);
			break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				if (game.getGame().getLandscape().getCollision().isCollidingY(game.getGame().getPlayer(), 1))
					game.getGame().getController().setVerticalVelocity(-35);
			break;
		}
	}
	
	public void onKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				game.getGame().getPlayer().right = false;
				if (game.getGame().getPlayer().left)
					game.getGame().getPlayer().setLastDirection(-1);
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				game.getGame().getPlayer().left = false;
				if (game.getGame().getPlayer().right)
					game.getGame().getPlayer().setLastDirection(1);
			break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
			break;
			case KeyEvent.VK_DOWN:
			break;
		}
	}

}
