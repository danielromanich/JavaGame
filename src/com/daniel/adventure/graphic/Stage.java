package com.daniel.adventure.graphic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.daniel.adventure.GameCore;

public abstract class Stage extends JPanel implements KeyListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7304435123578810985L;
	protected GameCore game;

	public Stage(GameCore game) {
		this.game = game;
		this.setLayout(null);
		this.addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
