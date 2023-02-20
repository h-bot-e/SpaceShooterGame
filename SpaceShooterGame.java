package com.yourname.spaceShooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceShooterGame extends JPanel implements ActionListener, KeyListener, MouseListener{

    private Timer timer = new Timer(5, this);
    private int x = 640, y = 360, size = 40;
    private double velX = 0, velY = 0, angle = 0;
    private boolean leftPressed = false, rightPressed = false, forwardPressed = false, backwardPressed = false;
    private final double acceleration = 0.2;
    private final double friction = 0.02;
    private int flameHeight = 20;
    private int asteroidSize = 100;  // add this line to define the size of the asteroid

    private List<Point> stars = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>(); // add this line to store the asteroids
    private boolean projectileFired = false;

    private Random rand = new Random(); // create a single Random object to generate star positions

    public SpaceShooterGame() {
        timer.start();
        addKeyListener(this);
        addMouseListener(this);  // Add a mouse listener instead of a key listener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Generate positions for the stars
        for (int i = 0; i < 50; i++) {
            int starX = rand.nextInt(1280);
            int starY = rand.nextInt(720);
            stars.add(new Point(starX, starY));
        }

        // Create the asteroids
        for (int i = 0; i < 5; i++) {
            int asteroidX = rand.nextInt(1280);
            int asteroidY = rand.nextInt(720);
            int asteroidSize = rand.nextInt(80) + 20;
            int asteroidVelocityX = rand.nextInt(5) - 2;
            int asteroidVelocityY = rand.nextInt(5) - 2;
            asteroids.add(new Asteroid(asteroidX, asteroidY, asteroidSize, asteroidVelocityX, asteroidVelocityY));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);

        // Draw the moving stars
        g.setColor(Color.WHITE);
        for (Point star : stars) {
            star.x -= 3;  // move the star to the left
            if (star.x < 0) {
                star.x = getWidth();  // wrap around to the right side of the screen
                star.y = rand.nextInt(getHeight());  // randomize the y position
            }
            g.fillRect(star.x, star.y, 2, 2);  // draw the star
        }

        // Draw the projectiles
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (Projectile projectile : projectiles) {
            g2d.fillOval(projectile.x, projectile.y, 5, 5);
        }
        
        

        // Draw the asteroids
        for (Asteroid asteroid : asteroids) {
        	if (asteroid.isVisible()) {
        	    g2d.setColor(Color.WHITE);
        	    g2d.fillOval(asteroid.getX(), asteroid.getY(), asteroid.getSize(), asteroid.getSize());
        	}
        }

        // Draw the spaceship
        int[] xPoints = {x, x - (size / 2), x, x + (size / 2)};
        int[] yPoints = {y - (size / 2), y + (size / 2), y + (size / 4), y + (size / 2)};
        Polygon p = new Polygon(xPoints, yPoints, 4);

        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.toRadians(angle), x, y);
        g2d.fill(p);
        g2d.rotate(-Math.toRadians(angle), x, y);

        if (forwardPressed) {
            // Draw the flame polygon
            int[] flameXPoints = {x - (size / 4), x, x + (size / 4)};
            int[] flameYPoints = {y + (size / 2), y + (size / 2) + (size / 4), y + (size / 2)};
            Polygon flame = new Polygon(flameXPoints, flameYPoints, 3);
            g2d.rotate(Math.toRadians(angle), x, y);
            g2d.setColor(Color.RED);
            g2d.fill(flame);

            // Randomly change the flame triangle size to create the flickering effect
            int newFlameHeight = (int) (flameHeight + Math.random() * 10 - 5);
            int[] newFlameYPoints = {y + (size / 2), y + (size / 2) + newFlameHeight, y + (size / 2)};
            Polygon flickerFlame = new Polygon(flameXPoints, newFlameYPoints, 3);
            g2d.setColor(Color.ORANGE);
            g2d.fill(flickerFlame);
            g2d.rotate(-Math.toRadians(angle), x, y); // undo rotation transform
        }
    }





    private long lastProjectileTime = 0;

    public void actionPerformed(ActionEvent e) {
        long currentMillis = System.currentTimeMillis();

        if (leftPressed) {
            angle -= 5;
        }
        if (rightPressed) {
            angle += 5;
        }
        if (forwardPressed) {
            velX += acceleration * Math.sin(Math.toRadians(angle));
            velY -= acceleration * Math.cos(Math.toRadians(angle));
        }
        if (backwardPressed) {
            velX -= acceleration * Math.sin(Math.toRadians(angle));
            velY += acceleration * Math.cos(Math.toRadians(angle));
        }
        velX *= (1 - friction);
        velY *= (1 - friction);

        // Check if spaceship is off screen and move it to the opposite side if necessary
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

        x += velX;
        y += velY;

        // Add a new projectile when the left mouse button is clicked
        if (projectileFired) {
            int projectileX = x + (int) (size * Math.sin(Math.toRadians(angle)));
            int projectileY = y - (int) (size * Math.cos(Math.toRadians(angle)));
            double projectileVelX = 20 * Math.sin(Math.toRadians(angle));
            double projectileVelY = -20 * Math.cos(Math.toRadians(angle));
            projectiles.add(new Projectile(projectileX, projectileY, projectileVelX, projectileVelY));
            projectileFired = false;
        }

        // Update projectile positions and remove projectiles that are off screen or have hit an asteroid
        List<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.setX(projectile.getX() + (int) projectile.getVelX());
            projectile.setY(projectile.getY() + (int) projectile.getVelY());
            if (projectile.getX() < 0 || projectile.getX() > getWidth() || projectile.getY() < 0 || projectile.getY() > getHeight()) {
                projectilesToRemove.add(projectile);
            } else {
                for (Asteroid asteroid : asteroids) {
                    int dx = projectile.getX() - asteroid.getX();
                    int dy = projectile.getY() - asteroid.getY();
                    int distance = (int) Math.sqrt(dx * dx + dy * dy);
                    if (distance < asteroid.getSize() / 2) {
                        asteroid.setVisible(false);
                        projectilesToRemove.add(projectile);
                        break;  // stop checking for other asteroid hits
                    }
                }
            }
        }
        projectiles.removeAll(projectilesToRemove);
        // Move the asteroids and check for asteroid collisions with the spaceship
        for (Asteroid asteroid : asteroids) {
            asteroid.move();
            asteroid.wrapPosition(getWidth(), getHeight());

            int dx = asteroid.getX() - x;
            int dy = asteroid.getY() - y;
            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            if (distance < (asteroid.getSize() / 2 + size / 2)) {
                System.out.println("You crashed into an asteroid!");
            }
        }

        // Update projectile positions and remove projectiles that are off screen or have hit an asteroid
        List<Projectile> projectilesToRemove1 = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.setX(projectile.getX() + (int) projectile.getVelX());
            projectile.setY(projectile.getY() + (int) projectile.getVelY());

            // Check for collisions with each asteroid
            for (Asteroid asteroid : asteroids) {
                int dx = asteroid.getX() - projectile.getX();
                int dy = asteroid.getY() - projectile.getY();
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance < asteroid.getSize() / 2) {
                    asteroid.setVisible(false);
                    projectilesToRemove1.add(projectile);
/*
                    // Split the asteroid into two smaller asteroids
                    if (asteroid.getSize() > 40) {
                        int newAsteroidSize = asteroid.getSize() / 2;
                        double newAsteroidVelX1 = (rand.nextDouble() - 0.5) * 4;
                        double newAsteroidVelY1 = (rand.nextDouble() - 0.5) * 4;
                        double newAsteroidVelX2 = (rand.nextDouble() - 0.5) * 4;
                        double newAsteroidVelY2 = (rand.nextDouble() - 0.5) * 4;
                        Asteroid newAsteroid1 = new Asteroid(asteroid.getX() - asteroid.getSize() / 4, asteroid.getY() - asteroid.getSize() / 4, newAsteroidVelX1, newAsteroidVelY1, newAsteroidSize);
                        Asteroid newAsteroid2 = new Asteroid(asteroid.getX() + asteroid.getSize() / 4, asteroid.getY() + asteroid.getSize() / 4, newAsteroidVelX2, newAsteroidVelY2, newAsteroidSize);
                        asteroids.add(newAsteroid1);
                        asteroids.add(newAsteroid2);
                    } */
                }
            }

            // Remove projectiles that are off screen
            if (projectile.getX() < 0 || projectile.getX() > getWidth() || projectile.getY() < 0 || projectile.getY() > getHeight()) {
                projectilesToRemove1.add(projectile);
            }
        }
        projectiles.removeAll(projectilesToRemove1);

        repaint();
    }



    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int projectileX = x + (int) (size * Math.sin(Math.toRadians(angle)));
            int projectileY = y - (int) (size * Math.cos(Math.toRadians(angle)));
            double projectileVelX = 20 * Math.sin(Math.toRadians(angle)); // increase the speed here
            double projectileVelY = -20 * Math.cos(Math.toRadians(angle)); // increase the speed here
            projectiles.add(new Projectile(projectileX, projectileY, projectileVelX, projectileVelY));
        }
    }
    
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // Do nothing
        }
    }


    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_W) {
            forwardPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            backwardPressed = true;
        }

        if (key == KeyEvent.VK_SPACE && !projectileFired) {
            int projectileX = x + (int) (size * Math.sin(Math.toRadians(angle)));
            int projectileY = y - (int) (size * Math.cos(Math.toRadians(angle)));
            double projectileVelX = 20 * Math.sin(Math.toRadians(angle)); // increase the speed here
            double projectileVelY = -20 * Math.cos(Math.toRadians(angle)); // increase the speed here
            projectiles.add(new Projectile(projectileX, projectileY, projectileVelX, projectileVelY));
            projectileFired = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_W) {
            forwardPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            backwardPressed = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            // Reset the projectileFired variable when the space key is released
            projectileFired = false;
        }
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Shooter Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpaceShooterGame game = new SpaceShooterGame();
        frame.add(game);
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
