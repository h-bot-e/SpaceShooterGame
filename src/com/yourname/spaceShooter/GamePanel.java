package com.yourname.spaceShooter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private StateManager stateManager;

    public GamePanel() {
        stateManager = new StateManager();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                GameState currentState = stateManager.getCurrentState();

//                if (currentState == GameState.TITLE) {
//                    if (stateManager.getTitleUI().isPlayButtonClicked(mouseX, mouseY)) {
//                       stateManager.startGame();
//
//                repaint(); // Add this line to refresh the panel after changing the game state.
//                    }
//                }
                // Handle other mouse events for different game states, for example: PauseUI
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        stateManager.render(g);
    }

    public void update() {
        stateManager.update();
    }
}
