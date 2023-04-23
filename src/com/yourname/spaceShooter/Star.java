package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star {
    private int x;
    private int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 2, 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
