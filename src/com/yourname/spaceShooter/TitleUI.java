package com.yourname.spaceShooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// The play button will need to trigger the game state
// Game.

public class TitleUI {
	
	public void render (Graphics g) {
		//Pause Title 
		Font fnt = new Font("Serial Roundhand",Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("TEST",(int) (Game.WIDTH / 1.5), 100);
	}

}
