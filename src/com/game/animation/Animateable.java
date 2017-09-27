package com.game.animation;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Animateable implements Renderable {
	
	protected ArrayList<ArrayList<Image>> animations = new ArrayList<>();
	private int animationSpeed = 10, animationTick = 0, standardAnimation = 0, currentAnimation = 0;
	private Image currentImage = null;
	private int animationNumber = 0;
	
	@Override
	public Image getRenderImage() {
		return currentImage == null ? animations.get(0).get(0) : currentImage;
	}
	
	public void animate() {
		if (animations.size() > 0) {
			if (animations.get(currentAnimation).size() == 1) {
				currentImage = animations.get(currentAnimation).get(0);
			} else {
				if (animationTick >= animationSpeed) {
					if (animationNumber < animations.get(currentAnimation).size() - 1) {
						animationNumber++;
						currentImage = animations.get(currentAnimation).get(animationNumber);
					} else {
						animationNumber = 0;
					}
					animationTick = 0;
				}
			}
			animationTick++;
		}
	}
	
	public int getAnimationSpeed() {
		return this.animationSpeed;
	}
	
	public int getStandardAnimation() {
		return this.standardAnimation;
	}
	
	public int getCurrentAnimation() {
		return this.currentAnimation;
	}
	
	public void playAnimation(int anim) {
		if (anim != standardAnimation && anim >= 0 && anim < animations.size())
			this.currentAnimation = anim;
	}
	
	public void setCurrentAnimation(int anim) {
		this.currentAnimation = anim;
	}
	
	public void setAnimationSpeed(int speed) {
		this.animationSpeed = speed;
	}

}
