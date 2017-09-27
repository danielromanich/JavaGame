package com.daniel.adventure.res;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	private final static HashMap<String, Image> imageMap = new HashMap<>();
	
	public static Image getImage(String name) {
		if (imageMap.containsKey(name)) {
			return imageMap.get(name);
		} else {
			Image i = null;
			try {
				i = ImageIO.read(new File("res/sprites/" + name+ ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (i != null) {
				imageMap.put(name, i);
			}
			return i;
		}
	}
}
