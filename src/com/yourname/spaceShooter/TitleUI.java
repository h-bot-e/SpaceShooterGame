package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TitleUI {
    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 140, 315, 200, 50);
    public Rectangle settingsButton = new Rectangle(Game.WIDTH / 2 + 140, 415, 200, 50);
    public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 140, 515, 200, 50);
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Title
        Font fnt = new Font("Serial Roundhand", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Space Shooter", (int) (Game.WIDTH / 1.7), 280);

        // Play button
        g2d.draw(playButton);
        Font playfnt = new Font("Serial Roundhand", Font.BOLD, 45);
        g.setFont(playfnt);
        g.drawString("Play", playButton.x + 55, playButton.y + 40);
    
        //Settings Button
        g2d.draw(settingsButton);
        Font settingsfnt = new Font("Serial Roundhand", Font.BOLD, 45);
        g.setFont(settingsfnt);
        g.drawString("Settings", settingsButton.x + 15, settingsButton.y + 40);
        
        //Exit Button
        g2d.draw(exitButton);
        Font exitfnt = new Font("Serial Roundhand", Font.BOLD, 25);
        g.setFont(exitfnt);
        g.drawString("Exit to Desktop", exitButton.x + 10, exitButton.y + 35);
        
    
    }

    public boolean isPlayButtonClicked(int mouseX, int mouseY) {
        return playButton.contains(mouseX, mouseY);
    }
}
