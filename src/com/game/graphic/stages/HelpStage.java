package com.game.graphic.stages;

import java.awt.Graphics;

import javax.swing.JButton;

import com.game.GameCore;
import com.game.graphic.Stage;
import com.game.res.ResourceLoader;

public class HelpStage extends Stage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2136626771571837125L;

	public HelpStage(GameCore game) {
		super(game);
		
		final JButton back = new JButton("Back to main menu");
		back.setBounds(300, 500, 200, 30);
		add(back);
		
		back.addActionListener((e) -> game.getGameClient().setStage(game.getGameClient().mainMenuStage));
		
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ResourceLoader.getImage("main_menu_background"), 0, 0, null);
		g.drawImage(ResourceLoader.getImage("help"), 200, 150, null);
		super.paintComponent(g);
	}
	
}
