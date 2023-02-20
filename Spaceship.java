package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;

public class Spaceship {
    private int x, y;
    
    public Spaceship() {
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;
    }
    
    public void update() {
        // update spaceship position here
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x - 10, y - 10, 20, 20);
    }
}
