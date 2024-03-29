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
	    
	    //Resume Button
	    g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect(resumeB.x, resumeB.y, resumeB.width, resumeB.height);    
		g2d.draw(resumeB);	
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serial Roundhand", Font.BOLD, 25));
        g2d.drawString("Resume", resumeB.x + 30, resumeB.y + 35);
        
        //Options Button
        g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect(optionsB.x, optionsB.y, optionsB.width, optionsB.height);    
		g2d.draw(optionsB);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serial Roundhand", Font.BOLD, 25));
        g2d.drawString("Options", optionsB.x + 30, optionsB.y + 35);
        
        //Back to Title Screen Button
        g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect(backB.x, backB.y, backB.width, backB.height);  
		g2d.draw(backB);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serial Roundhand", Font.BOLD, 16));
        g2d.drawString("Exit to Main Menu", backB.x + 10, backB.y + 35);
		
		//Exit to Desktop Button
        g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect(exitB.x, exitB.y, exitB.width, exitB.height); 
		g2d.draw(exitB);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serial Roundhand", Font.BOLD, 16));
        g2d.drawString("Exit to Desktop", exitB.x + 18, exitB.y + 35);
		}
//	}

// public void setIsPaused(boolean isPaused) {
//    this.isPaused = isPaused;
// }
	
}