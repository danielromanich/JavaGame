package com.daniel.adventure.graphic.stages;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import com.daniel.adventure.GameCore;
import com.daniel.adventure.graphic.Stage;
import com.daniel.adventure.res.ResourceLoader;

public class MainMenuStage extends Stage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388412542689446039L;

	public MainMenuStage(GameCore game) {
		super(game);

		final JButton start = new JButton("Play");
		start.setBounds(300, 150, 200, 30);
		add(start);
		
		start.addActionListener((e) -> game.getGameClient().setStage(game.getGameClient().gameStage));
		
		final JButton help = new JButton("Help");
		help.setBounds(300, 190, 200, 30);
		add(help);
		
		help.addActionListener((e) -> game.getGameClient().setStage(game.getGameClient().helpStage));
		
		final JButton settings = new JButton("Settings");
		settings.setBounds(300, 230, 200, 30);
		add(settings);
		
		settings.addActionListener((e) -> game.getGameClient().setStage(game.getGameClient().settingsStage));
		
		this.setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ResourceLoader.getImage("main_menu_background"), 0, 0, null);
		super.paintComponent(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
