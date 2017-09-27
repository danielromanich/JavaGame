package com.game.entity.objects;

import java.awt.Image;

import com.game.Game;
import com.game.entity.GameObject;
import com.game.entity.PhysicsEntity;
import com.game.entity.entities.Player;
import com.game.res.ResourceLoader;
import com.game.util.Position;

public class MysteryBox extends GameObject {

	public MysteryBox(Game game, int offsetX, int offsetY, Position position) {
		super(game, offsetX, offsetY, 32, 32);
		this.setPosition(position);
	}
	
	private boolean opened = false;

	@Override
	public void onCollision(PhysicsEntity e, Position direction) {
		if (direction.getY() == -1 && e instanceof Player) {
			opened = true;
		}
	}
	
	@Override
	public Image getRenderImage() {
		return opened ? ResourceLoader.getImage("objects/empty_box") : ResourceLoader.getImage("objects/mystery_box");
	}

}
