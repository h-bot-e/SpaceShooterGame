package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class Asteroid {
    private int x, y;
    private int size;
    private double velX, velY;
    private boolean visible;

    public Asteroid(int x, int y, int size, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velX = velX;
        this.velY = velY;
        this.visible = true;
    }
    
    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }
    
    public void setX(double x) {
        this.x = (int) x;
    }

    
    public void setY(double y) {
        this.y = (int) y;
    }



    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, size, size);
    }

    public void move() {
        x += velX;
        y += velY;

        if (x < -size/2) {
            x = 1280 + size/2;
        } else if (x > 1280 + size/2) {
            x = -size/2;
        }
        if (y < -size/2) {
            y = 720 + size/2;
        } else if (y > 720 + size/2) {
            y = -size/2;
        }
    }

    public boolean collidesWith(Projectile projectile) {
        int dx = projectile.getX() - x;
        int dy = projectile.getY() - y;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return (distance < size/2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

	public void wrapPosition(int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
