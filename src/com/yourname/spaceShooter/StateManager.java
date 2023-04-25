package com.yourname.spaceShooter;

import java.awt.Graphics;

public class StateManager {
    private GameState currentState;
    private TitleUI titleUI;
    private PauseUI pauseUI;
    private Game game;

    
    public void startGame() {
        setCurrentState(GameState.PLAYING);
    }

    public void pauseGame() {
        setCurrentState(GameState.PAUSED);
    }

    public void resumeGame() {
        setCurrentState(GameState.PLAYING);
    }
    
    public TitleUI getTitleUI() {
        return titleUI;
    }

    
    public StateManager() {
        currentState = GameState.TITLE;
        titleUI = new TitleUI();
        pauseUI = new PauseUI();
        game = new Game();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void update() {
        switch (currentState) {
            case PLAYING:
                game.update();
                break;
            case PAUSED:
            case TITLE:
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch (currentState) {
            case TITLE:
                titleUI.render(g);
                break;
            case PLAYING:
                game.draw(g);
                break;
            case PAUSED:
                pauseUI.render(g);
                break;
            default:
                break;
        }
    }

    // Add methods to handle user input and game state transitions.
    // Example: startGame(), pauseGame(), resumeGame(), etc.
}
