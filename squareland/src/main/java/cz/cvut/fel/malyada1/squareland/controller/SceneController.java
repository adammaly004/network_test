package cz.cvut.fel.malyada1.squareland.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import cz.cvut.fel.malyada1.squareland.view.GameView;
import cz.cvut.fel.malyada1.squareland.view.MenuView;
import cz.cvut.fel.malyada1.squareland.view.MapEditorView;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.*;

public class SceneController {
    private final Stage primaryStage;
    private final Scene scene;

    private GameController gameController;
    private MenuController menuController;
    private MapEditorController mapEditorController;

    private GameView gameView;
    private MenuView menuView;
    private MapEditorView mapEditorView;

    public SceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.scene = new Scene(new Pane(), WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple game engine");
        primaryStage.setResizable(false);

        initializeViews();
        initializeControllers();

        //setKeyHandlers();
    }

    private void initializeViews() {
        menuView = new MenuView(WIDTH, HEIGHT);
        gameView = new GameView(WIDTH, HEIGHT);
        mapEditorView = new MapEditorView(WIDTH, HEIGHT);
    }

    private void initializeControllers() {
        menuController = new MenuController(menuView, this);
        gameController = new GameController(gameView, this);
        mapEditorController = new MapEditorController(mapEditorView, this);
    }

//    private void setKeyHandlers() {
//        scene.setOnKeyPressed(e -> gameController.handleKeyPress(e.getCode()));
//        scene.setOnKeyReleased(e -> gameController.handleKeyRelease(e.getCode()));
//    }

    public void chooseMapFile(){
        gameController.chooseMapFile();
    }

    public void showMenu() {
        if(gameController != null) {
            gameController.pauseGame();
        }
        scene.setRoot(menuView.getRoot());
        primaryStage.setTitle("Square Land - Menu");
    }

    public void startGame() {
        scene.setRoot(gameView.getRoot());
        primaryStage.setTitle("Square Land - Game");

        if(gameController.getIsPaused()){
            gameController.resumeGame();
        }else{
            gameController.startGame();
        }
    }

    public void startEditor() {
        scene.setRoot(mapEditorView.getRoot());
        primaryStage.setTitle("Square Land - Map Editor");
        mapEditorController.startEditor();
    }

    public void exitGame() {
        primaryStage.close();
    }
    public Scene getScene() {
        return scene;
    }
}
