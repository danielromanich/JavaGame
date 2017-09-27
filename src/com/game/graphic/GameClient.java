package com.daniel.adventure.graphic;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import com.daniel.adventure.GameCore;
import com.daniel.adventure.graphic.stages.GameStage;
import com.daniel.adventure.graphic.stages.HelpStage;
import com.daniel.adventure.graphic.stages.MainMenuStage;
import com.daniel.adventure.graphic.stages.SettingsStage;
import com.daniel.adventure.levels.Level1;
import com.daniel.adventure.util.Data;

public class GameClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3191138489009695926L;
	private GameCore game;
	private Stage currentStage;
	
	public GameClient(GameCore game) {
		this.game = game;
		this.mainMenuStage = new MainMenuStage(game);
		this.settingsStage = new SettingsStage(game);
		this.gameStage = new GameStage(game);
		this.helpStage = new HelpStage(game);
		init();
	}
	
	public Stage mainMenuStage, gameStage, settingsStage, helpStage;
	
	public void setStage(Stage stage) {		
		for (Component c : this.getContentPane().getComponents()) {
			if (c instanceof Stage) {
				this.getContentPane().remove(c);
			}
		}
		this.add(stage);
		this.currentStage = stage;
		if (stage instanceof GameStage) {
			this.setResizable(true);
			this.setSize(1200, 800);
			game.newGame(new Level1(game));
			game.getGame().getCamera().setX(game.getGame().getCamera().getX() + game.getGame().getPlayer().getVelocityX(), game.getGame().getPlayer());
			((GameStage) stage).start();
			((GameStage) stage).repaint();
		} else {
			this.setSize(800, 600);
			this.setResizable(false);
		}
		stage.requestFocus();
		this.getContentPane().revalidate();
		for (Component c : stage.getComponents())
			c.repaint();
	}
	
	public Stage getCurrentStage() {
		return this.currentStage;
	}
	
	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Data.WIDTH, Data.HEIGHT);
		setTitle("The adventure");
		setStage(mainMenuStage);
		
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				Data.WIDTH = getContentPane().getWidth();
				Data.HEIGHT = getContentPane().getHeight();
				if (game.getGame() != null)
					game.getGame().getCamera().setX(game.getGame().getPlayer().getPosition().getX() * Data.TILE_WIDTH + game.getGame().getPlayer().getSmallX(), game.getGame().getPlayer());
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				Data.WIDTH = getContentPane().getWidth();
				Data.HEIGHT = getContentPane().getHeight();
				if (game.getGame() != null)
					game.getGame().getCamera().setX(game.getGame().getPlayer().getPosition().getX() * Data.TILE_WIDTH + game.getGame().getPlayer().getSmallX(), game.getGame().getPlayer());
			}

			@Override
			public void componentShown(ComponentEvent arg0) {}
			
		});
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(1);
			}

			@Override
			public void windowClosing(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
			
		});
		
		setVisible(true);
		
	}

}
