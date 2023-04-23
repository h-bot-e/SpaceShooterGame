package com.yourname.spaceShooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;


public class PauseUI {
	
	//Creating the Buttons for the pause menu
	public Rectangle resumeB = new Rectangle ((int) (Game.WIDTH / 1.5 + 28), 145, 150, 50);
	public Rectangle optionsB = new Rectangle ((int) (Game.WIDTH / 1.5 + 28), 245, 150, 50);
	public Rectangle backB = new Rectangle ((int) (Game.WIDTH / 1.5 + 28), 345, 150, 50);
	public Rectangle exitB = new Rectangle ((int) (Game.WIDTH / 1.5 + 28), 445, 150, 50);
	
	
	public void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//Pause Title 
		Font fnt = new Font("Serial Roundhand",Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("PAUSED",(int) (Game.WIDTH / 1.5), 100);
		
		//Buttons for Pause Menu
		g2d.draw(resumeB);
		g2d.draw(optionsB);
		g2d.draw(backB);
		g2d.draw(exitB);
	}
	
	
	
	
	
}