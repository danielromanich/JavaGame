package com.game.graphic.stages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.game.GameCore;
import com.game.graphic.Stage;
import com.game.res.ResourceLoader;

public class SettingsStage extends Stage {
	
	private JPanel settingsPanel = new JPanel();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895565911959351775L;

	public SettingsStage(GameCore game) {
		super(game);
		
		this.setOpaque(false);
		
		settingsPanel.setLayout(null);
		settingsPanel.setBounds(200, 120, 400, 680);
		add(settingsPanel);
		settingsPanel.setOpaque(false);
		
		final JButton muteButton = new JButton(game.getSettings().isMuted() ? "Unmute" : "Mute");
		muteButton.setBounds(100, 30, 200, 30);
		settingsPanel.add(muteButton);
		
		muteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.getSettings().setMuted(!game.getSettings().isMuted());
				muteButton.setText(game.getSettings().isMuted() ? "Unmute" : "Mute");
			}
			
		});
		
		final JLabel volumeLabel = new JLabel("Volume: ");
		volumeLabel.setForeground(Color.WHITE);
		volumeLabel.setBounds(100 - volumeLabel.getPreferredSize().width - 5, 67, volumeLabel.getPreferredSize().width, 30);
		settingsPanel.add(volumeLabel);
		
		final JSlider volume = new JSlider(0, 100, game.getSettings().getVolume());
		volume.setBounds(100, 70, 200, 30);
		volume.setOpaque(false);
		settingsPanel.add(volume);
		
		volume.addChangeListener((e) -> game.getSettings().setVolue(volume.getValue()));
		
		final JButton back = new JButton("Back");
		back.setBounds(100, 110, 200, 30);
		settingsPanel.add(back);
		
		final JCheckBox debugPlayer = new JCheckBox("Debug player");
		debugPlayer.setForeground(Color.WHITE);
		debugPlayer.setBounds(100, 150, 150, 25);
		debugPlayer.setOpaque(false);
		settingsPanel.add(debugPlayer);
		
		debugPlayer.addActionListener((e) -> game.getSettings().setDebugPlayer(debugPlayer.isSelected()));
		
		final JCheckBox debugObjects = new JCheckBox("Debug objects");
		debugObjects.setForeground(Color.WHITE);
		debugObjects.setBounds(100, 175, 150, 25);
		debugObjects.setOpaque(false);
		settingsPanel.add(debugObjects);
		
		debugObjects.addActionListener((e) -> game.getSettings().setDebugObjects(debugObjects.isSelected()));
		
		final JCheckBox debugEntities = new JCheckBox("Debug entities");
		debugEntities.setForeground(Color.WHITE);
		debugEntities.setBounds(100, 200, 150, 25);
		debugEntities.setOpaque(false);
		settingsPanel.add(debugEntities);
		
		debugEntities.addActionListener((e) -> game.getSettings().setDebugEntities(debugEntities.isSelected()));
		
		final JCheckBox debugTiles = new JCheckBox("Debug tiles");
		debugTiles.setForeground(Color.WHITE);
		debugTiles.setBounds(100, 225, 150, 25);
		debugTiles.setOpaque(false);
		settingsPanel.add(debugTiles);
		
		debugTiles.addActionListener((e) -> game.getSettings().setDebugTiles(debugTiles.isSelected()));
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (game.getGameClient().getCurrentStage().equals(game.getGameClient().gameStage)) {
					game.getGameClient().getCurrentStage().remove(getSettingsPanel());
					for (Component c : game.getGameClient().getCurrentStage().getComponents())
						c.setVisible(true);
					settingsPanel.setBounds(200, 120, 400, 680);
					add(settingsPanel);
				} else {
					game.getGameClient().setStage(game.getGameClient().mainMenuStage);
				}
			}
			
		});
	}
	
	public JPanel getSettingsPanel() {
		return this.settingsPanel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ResourceLoader.getImage("settings_background"), 0, 0, this);
		super.paintComponent(g);
	}

}
