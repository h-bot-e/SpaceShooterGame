package com.yourname.spaceShooter;

import java.awt.Point;
import java.awt.Rectangle;

public class Projectile {
    int x;
    int y;
    private double velX;
    private double velY;

    public Projectile(int x, int y, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
