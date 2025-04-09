package cz.cvut.fel.malyada1.squareland.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import cz.cvut.fel.malyada1.squareland.model.Game;
import cz.cvut.fel.malyada1.squareland.view.GameView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
    private final Game model;
    private final GameView view;
    private final Set<KeyCode> keyPress;
    private final SceneController sceneController;
    private AnimationTimer gameLoop;
    private boolean isPaused;

    private final List<Rectangle> items = new ArrayList<>();
    int timer = 0;

    public GameController(GameView view, SceneController sceneController) {
        model = new Game();
        this.view = view;
        this.sceneController = sceneController;
        this.isPaused = false;
        keyPress = new HashSet<>();

        setKeyHandlers();
    }

    public void spawnerTimer(){
        if (timer > 50) {
            Rectangle i = model.getItemSpawner().spawnItems();
            if (i != null) {
                items.add(i);
            }
            timer = 0;
        }
        timer++;
    }

    public void startGame() {
        view.drawMap(model.getMap());
        view.drawPlayers(model.getPlayer1(), model.getPlayer2());

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
    }
    private void setKeyHandlers(){
        sceneController.getScene().setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        sceneController.getScene().setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
    }

    private void handleKeyPress(KeyCode code) {
        if(code == KeyCode.ESCAPE){
            pauseGame();
            sceneController.showMenu();
        }
        keyPress.add(code);
    }

    public void chooseMapFile(){
        model.chooseMapFile();
        restartGame();
    }

    private void handleKeyRelease(KeyCode code) {
        keyPress.remove(code);
    }

    private void update() {
        model.getPlayer1().update(keyPress);
        model.getPlayer2().update(keyPress);

        model.getPlayer1().bulletCollision(model.getPlayer2().getBullets());
        model.getPlayer2().bulletCollision(model.getPlayer1().getBullets());

        model.getPlayer1().itemCollision(items);
        model.getPlayer2().itemCollision(items);

        spawnerTimer();
        view.drawItems(items);


        view.drawBullets(model.getPlayer1().getBullets(), model.getPlayer2().getBullets());
    }

    public void pauseGame(){
        if (gameLoop != null) {
            gameLoop.stop();
            isPaused = true;
        }
    }

    public void resumeGame(){
        if (isPaused) {
            gameLoop.start();
            isPaused = false;
        }
    }

    private void restartGame(){
        model.reset();
        items.clear();
        view.getRoot().getChildren().clear();

        if (gameLoop != null) {
            gameLoop.stop();
        }
        isPaused = false;
    }
    private void exitGame(){
        sceneController.exitGame();
    }

    public boolean getIsPaused(){
        return isPaused;
    }

}

