package com.daniel.adventure.entity.objects;

import java.awt.Image;

import com.daniel.adventure.Game;
import com.daniel.adventure.entity.GameObject;
import com.daniel.adventure.entity.PhysicsEntity;
import com.daniel.adventure.entity.entities.Player;
import com.daniel.adventure.res.ResourceLoader;
import com.daniel.adventure.util.Position;

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
