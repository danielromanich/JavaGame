package com.game.util;

public class Position {
	
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + getX() + ", " + getY() + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position p = (Position) o;
			return p.getX() == getX() && p.getY() == getY();
		}
		return false;
	}
	
	public int getDistance(Position pos) {
		return (int) Math.sqrt(Math.pow(pos.getX() - getX(), 2) + Math.pow(pos.getY() - getY(), 2));
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}
