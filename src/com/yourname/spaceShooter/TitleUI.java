package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TitleUI {
    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 75, 300, 150, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Title
        Font fnt = new Font("Serial Roundhand", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Space Shooter", Game.WIDTH / 2 - 150, 200);

        // Play button
        g2d.draw(playButton);
        g.drawString("Play", playButton.x + 45, playButton.y + 35);
    }

    public boolean isPlayButtonClicked(int mouseX, int mouseY) {
        return playButton.contains(mouseX, mouseY);
    }
}
