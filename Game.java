package com.yourname.spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int NUM_STARS = 100;

    private Star[] stars;
    private Spaceship spaceship;

    public Game() {
        stars = new Star[NUM_STARS];
        for (int i = 0; i < NUM_STARS; i++) {
            int x = new Random().nextInt(WIDTH);
            int y = new Random().nextInt(HEIGHT);
            stars[i] = new Star(x, y);
        }
        spaceship = new Spaceship();
    }

    public void update() {
        spaceship.update();
    }

    public void draw(Graphics g) {

        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the stars
        g.setColor(Color.WHITE);
        for (Star star : stars) {
            g.fillOval(star.getX(), star.getY(), 2, 2);
        }

        // Draw the spaceship
        spaceship.draw(g);
    }
}
