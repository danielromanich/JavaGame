package com.daniel.adventure.graphic.stages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import com.daniel.adventure.GameCore;
import com.daniel.adventure.graphic.Stage;
import com.daniel.adventure.util.Data;

public class GameStage extends Stage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -758479723413382130L;
	
	public GameStage(GameCore game) {
		super(game);
	}
	
	private Thread paintThread;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.getGameRenderer().renderGame(g);
		if (game.getGame().isDead()) {
			g.setColor(new Color(0, 0, 0, 220));
			g.fillRect(0, 0, Data.WIDTH, Data.HEIGHT);
		} else if (game.getGame().isPaused()) {
			g.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, Data.WIDTH, Data.HEIGHT);
			g.setColor(Color.white);
			g.setFont(new Font("Comic sans", Font.BOLD, 30));
			g.drawString("Paused", Data.WIDTH / 2 - 60, 100);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.getKeyboard().onKeyEntered(e);
		if (!game.getGame().isDead()) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !game.getGame().isPaused()) {
				game.getGame().setPaused(true);
				JButton resume = new JButton("Resume");
				resume.setBounds(Data.WIDTH / 2 - 100, 200, 200, 30);
				add(resume);
				
				JButton settings = new JButton("Settings");
				settings.setBounds(Data.WIDTH / 2 - 100, 240, 200, 30);
				add(settings);
				
				JButton mainmenu = new JButton("Main menu");
				mainmenu.setBounds(Data.WIDTH / 2 - 100, 280, 200, 30);
				add(mainmenu);
				
				resume.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						game.getGame().setPaused(false);
						removeAll();
						requestFocus();
					}
					
				});
				
				settings.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for (Component c : getComponents())	
							c.setVisible(false);
						((SettingsStage) game.getGameClient().settingsStage).getSettingsPanel().setBounds(Data.WIDTH / 2 - 200, 150, 400, 680);
						add(((SettingsStage) game.getGameClient().settingsStage).getSettingsPanel());
					}
					
				});
				
				mainmenu.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						removeAll();
						game.getGameClient().setStage(game.getGameClient().mainMenuStage);
						game.getGameClient().mainMenuStage.repaint();
						requestFocus();
					}
					
				});
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && game.getGame().isPaused()) {
				removeAll();
				requestFocus();
				game.getGame().setPaused(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.getKeyboard().onKeyReleased(e);
	}

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
	
	public void start() {
		if (paintThread == null) {
			paintThread = new Thread(new Runnable() {
	
				@Override
				public void run() {
					while (true) {
						long start = System.currentTimeMillis();
						if (!game.getGame().isPaused()) {
							game.getGame().getEntities().animateAll();
							game.getGame().getPlayer().animate();
							game.getGame().getGameObjects().animateAll();
						}
						repaint();
						long delta = System.currentTimeMillis() - start;
						try {
							Thread.sleep(1000/Data.GAME_FPS - delta < 0 ? 0 : 1000/Data.GAME_FPS - delta);
						} catch (InterruptedException e) {}
					}
				}
				
			});
		}
		if (!paintThread.isAlive())
			paintThread.start();
	}

}
